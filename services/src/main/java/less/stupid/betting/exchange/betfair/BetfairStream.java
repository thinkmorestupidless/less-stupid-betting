package less.stupid.betting.exchange.betfair;

import akka.Done;
import com.betfair.aping.entities.MarketFilter;
import com.typesafe.config.Config;
import less.stupid.betting.exchange.betfair.api.exchange.stream.ResponseMessage;
import less.stupid.betting.exchange.betfair.api.exchange.stream.StatusMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicInteger;

public class BetfairStream {

    private static final Logger log = LoggerFactory.getLogger(BetfairStream.class);

    private final AtomicInteger counter = new AtomicInteger();

    private StreamSocket socket;

    public BetfairStream(SessionProvider sessions, DefaultStreamRequestFactory requests, Config config) {
        socket = new StreamSocket(sessions, requests, config);
    }

    public CompletionStage<Done> connect() {
        return socket.authenticate().thenApply(response -> {
            if (response instanceof StatusMessage) {
                StatusMessage status = (StatusMessage) response;

                if (status.getStatusCode().equals("STATUS")) {
                    return Done.getInstance();
                }

                throw new RuntimeException(String.format("Failed to connect -> %s", response));
            }

            throw new RuntimeException(String.format("Failed to connect -> %s", response));
        });
    }

    public CompletionStage<ResponseMessage> subscribe(MarketFilter marketFilter, StreamRequest.MarketDataFilter marketDataFilter) {
        log.info("subscribing to market {}", marketFilter);
        return socket.authenticate().thenCompose(response -> {
            log.info("authenticated, sending");
            return socket.send(new StreamRequest.MarketSubscription(counter.getAndIncrement(), marketFilter, marketDataFilter));
        });
    }
}
