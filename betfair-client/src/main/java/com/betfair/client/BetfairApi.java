package com.betfair.client;

import com.betfair.aping.containers.EventResultContainer;
import com.betfair.aping.containers.EventTypeResultContainer;
import com.betfair.aping.containers.ListMarketCatalogueContainer;
import com.betfair.aping.entities.MarketFilter;
import com.betfair.aping.enums.MarketProjection;
import com.betfair.aping.enums.MarketSort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.CompletionStage;

import static com.opengamma.strata.collect.Unchecked.wrap;

public class BetfairApi implements ApiConstants, ApiOperations {

    private static final Logger log = LoggerFactory.getLogger(BetfairApi.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final BetfairConnection connection;

    private final ExchangeApi exchange;

    public BetfairApi(BetfairConnection connection) {
        this.connection = connection;

        exchange = new ExchangeApi(connection);
    }

    public CompletionStage<LoginResponse> login() {
        return connection.connect()
                .exceptionally(t -> {
                    log.error("error logging in -> {}", t);
                    return null;
                })
                .thenApply(resp -> wrap(() -> mapper.readValue(resp, LoginResponse.class)));
    }

    public CompletionStage<EventTypeResultContainer> listEventTypes(MarketFilter filter) {
        return exchange.listEventTypes(filter);
    }

    public CompletionStage<EventResultContainer> listEvents(MarketFilter filter) {
        return exchange.listEvents(filter);
    }

    public CompletionStage<ListMarketCatalogueContainer> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection, MarketSort sort, int maxResults) {
        return exchange.listMarketCatalogue(filter, marketProjection, sort, maxResults);
    }
}
