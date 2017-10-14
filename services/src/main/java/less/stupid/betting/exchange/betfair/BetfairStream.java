package less.stupid.betting.exchange.betfair;

import akka.Done;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionStage;

public class BetfairStream {

    private static final Logger log = LoggerFactory.getLogger(BetfairStream.class);

    private StreamSocket socket;

    public BetfairStream(SessionProvider sessions, DefaultStreamRequestFactory requests, Config config) {
        socket = new StreamSocket(sessions, requests, config);
    }

    public CompletionStage<Done> connect() {
        return socket.authenticate().thenApply(response -> {
            if (response instanceof StreamResponse.Status) {
                StreamResponse.Status status = (StreamResponse.Status) response;

                if (status.getStatusCode().equals("STATUS")) {
                    return Done.getInstance();
                }

                throw new RuntimeException(String.format("Failed to connect -> %s", response));
            }

            throw new RuntimeException(String.format("Failed to connect -> %s", response));
        });
    }
}
