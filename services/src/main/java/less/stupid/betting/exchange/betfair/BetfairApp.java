package less.stupid.betting.exchange.betfair;

import com.betfair.aping.entities.MarketFilter;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class BetfairApp {

    private static final Logger log = LoggerFactory.getLogger(BetfairApp.class);

    public static void main(String[] args) {
        testMarketCollection();
    }

    public static void testMarketCollection() {
        BetfairClient client = new BetfairClient();

        client.listEventTypes(new MarketFilter())
                .exceptionally(t -> {
                    log.error("error listing event types -> {}", t);
                    return Collections.emptyList();
                }).thenAccept(result -> log.info("result is -> {}", result));
    }

    public static void testStream() {
        DefaultStreamRequestFactory requests = new DefaultStreamRequestFactory();

        BetfairClient client = new BetfairClient();
        BetfairStream stream = new BetfairStream(client, requests, ConfigFactory.load());

        stream.connect().toCompletableFuture().join();
    }
}
