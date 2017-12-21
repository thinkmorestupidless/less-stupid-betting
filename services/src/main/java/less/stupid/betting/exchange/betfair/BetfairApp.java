package less.stupid.betting.exchange.betfair;

import com.betfair.aping.entities.EventResult;
import com.betfair.aping.entities.MarketCatalogue;
import com.betfair.aping.entities.MarketFilter;
import com.betfair.aping.enums.MarketProjection;
import com.betfair.aping.enums.MarketSort;
import com.betfair.client.BetfairClient;
import com.betfair.client.asynchttp.AsyncHttpBetfairClient;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;

public class BetfairApp {

    private static final Logger log = LoggerFactory.getLogger(BetfairApp.class);

    public static void main(String[] args) {
        testMarketCollection();
    }

    public static void testMarketCollection() {
        BetfairClient client = new AsyncHttpBetfairClient();

//        client.listEventTypes(new MarketFilter())
//                .exceptionally(t -> {
//                    log.error("error listing event types -> {}", t);
//                    return Collections.emptyList();
//                }).thenAccept(result -> log.info("result is -> {}", result));

        MarketFilter filter = MarketFilter.builder()
                .eventTypeIds(Sets.newHashSet("1"))
                .marketCountries(Sets.newHashSet("GB"))
                .build();

        client.listEvents(filter)
                .exceptionally(t -> {
                    log.error("error listing events -> {}", t);
                    return Collections.emptyList();
                }).thenAccept(result -> {
                    log.info("({} events) result is -> {}", result.size(), result);

                    if (result.size() > 0) {
                        EventResult first = result.get(0);

                        MarketFilter filter2 = MarketFilter.builder()
                                .eventIds(Sets.newHashSet(first.getEvent().getId()))
                                .build();

                        Set<MarketProjection> projections = Sets.newHashSet(MarketProjection.COMPETITION, MarketProjection.RUNNER_DESCRIPTION);
                        MarketSort sort = MarketSort.MAXIMUM_TRADED;


                        client.listMarketCatalogue(filter, projections, sort, 100)
                                .exceptionally(t -> {
                                    log.info("failed to get market catalogue, {}", t);
                                    return Collections.emptyList();
                                }).thenAccept(catalogues -> {
                            log.info("market catalogue for {} is {}", first.getEvent().getId(), catalogues);

                            for (MarketCatalogue catalogue : catalogues) {
                                if (catalogue.getMarketName().equals("Match Odds")) {
                                    log.info("subscribing to market {}", catalogue.getMarketId());

                                    MarketFilter streamFilter = MarketFilter.builder()
                                            .marketIds((Sets.newHashSet(catalogue.getMarketId())))
                                            .build();

                                    client.subscribeToMarket(streamFilter, null).thenAccept(response -> {
                                        log.info("response -> " + response);
                                    });
                                }
                            }
                        });
                    }
        });
    }

//    public static void testStream() {
//        DefaultStreamRequestFactory requests = new DefaultStreamRequestFactory();
//
//        BetfairClient client = new BetfairClient();
//        BetfairStream stream = new BetfairStream(client, requests, ConfigFactory.load());
//
//        stream.connect().toCompletableFuture().join();
//    }
}
