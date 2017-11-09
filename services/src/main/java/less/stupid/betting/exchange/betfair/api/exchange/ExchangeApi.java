package less.stupid.betting.exchange.betfair.api.exchange;

import com.betfair.aping.containers.EventResultContainer;
import com.betfair.aping.containers.EventTypeResultContainer;
import com.betfair.aping.containers.ListMarketCatalogueContainer;
import com.betfair.aping.entities.MarketFilter;
import com.betfair.aping.enums.MarketProjection;
import com.betfair.aping.enums.MarketSort;
import com.fasterxml.jackson.databind.ObjectMapper;
import less.stupid.betting.exchange.betfair.BetfairConnection;
import less.stupid.betting.exchange.betfair.JsonRequest;
import org.omg.CORBA.CompletionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletionStage;

import static com.opengamma.strata.collect.Unchecked.wrap;

public class ExchangeApi implements ExchangeConstants, ExchangeOperations {

    private static final Logger log = LoggerFactory.getLogger(ExchangeApi.class);

    private final Locale locale = Locale.getDefault();

    private final ObjectMapper mapper = new ObjectMapper();

    private final BetfairConnection connection;

    public ExchangeApi(BetfairConnection connection) {
        this.connection = connection;
    }

    public CompletionStage<EventTypeResultContainer> listEventTypes(MarketFilter filter) {
        log.info("listEventTypes({})", filter);

        JsonRequest request = JsonRequest.create()
                                         .withMethod(LIST_EVENT_TYPES)
                                         .withParam(FILTER, filter)
                                         .withParam(LOCALE, locale);

        return execute(jsonify(request), EventTypeResultContainer.class);
    }

    public CompletionStage<EventResultContainer> listEvents(MarketFilter filter) {
        log.info("listEvents({})", filter);

        JsonRequest request = JsonRequest.create()
                                         .withMethod(LIST_EVENTS)
                                         .withParam(FILTER, filter)
                                         .withParam(LOCALE, locale);

        return execute(jsonify(request), EventResultContainer.class);
    }

    /**
     * Returns a list of information about published (ACTIVE/SUSPENDED) markets that does not change (or changes very rarely).
     * You use listMarketCatalogue to retrieve the name of the market, the names of selections and other information about markets.
     * Market Data Request Limits apply to requests made to listMarketCatalogue.
     *
     * @param filter The filter to select desired markets. All markets that match the criteria in the filter are selected.
     * @param marketProjection The type and amount of data returned about the market.
     * @param sort The order of the results. Will default to RANK if not passed. RANK is an assigned priority that is determined by our Market Operations team in our back-end system. A result's overall rank is derived from the ranking given to the flowing attributes for the result. EventType, Competition, StartTime, MarketType, MarketId. For example, EventType is ranked by the most popular sports types and marketTypes are ranked in the following order: ODDS ASIAN LINE RANGE If all other dimensions of the result are equal, then the results are ranked in MarketId order.
     * @param maxResults limit on the total number of results returned, must be greater than 0 and less than or equal to 1000
     *
     * @return
     */
    public CompletionStage<ListMarketCatalogueContainer> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection, MarketSort sort, int maxResults) {
        log.info("listMarketCatalogue({},{},{},{})", filter, marketProjection, sort, maxResults);

        JsonRequest request = JsonRequest.create()
                                         .withMethod(LIST_MARKET_CATALOGUE)
                                         .withParam(FILTER, filter)
                                         .withParam(MARKET_PROJECTION, marketProjection)
                                         .withParam(SORT, sort)
                                         .withParam(MAX_RESULTS, maxResults)
                                         .withParam(LOCALE, locale);

        return execute(jsonify(request), ListMarketCatalogueContainer.class);
    }

    public <T> CompletionStage<T> execute(String request, Class<T> clazz) {
        return connection.execute(request)
                         .thenApply(body -> {
                             log.info("response -> {}", body);
                             return objectify(body, clazz);
                         });
    }

    public String jsonify(Object o) {
        return wrap(() -> mapper.writeValueAsString(o));
    }

    public <T> T objectify(String s, Class<T> clazz) {
        return wrap(() -> mapper.readValue(s, clazz));
    }
}
