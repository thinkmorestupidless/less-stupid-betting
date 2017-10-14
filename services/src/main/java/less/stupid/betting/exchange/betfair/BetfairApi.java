package less.stupid.betting.exchange.betfair;

import com.betfair.aping.containers.EventTypeResultContainer;
import com.betfair.aping.entities.MarketFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.concurrent.CompletionStage;

import static com.opengamma.strata.collect.Unchecked.wrap;

public class BetfairApi implements ApiConstants, ApiOperations {

    private static final Logger log = LoggerFactory.getLogger(BetfairApi.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final BetfairConnection connection;

    public BetfairApi(BetfairConnection connection) {
        this.connection = connection;
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
        log.info("listing event types");

        JsonRequest request = JsonRequest.create()
                .withMethod(LIST_EVENT_TYPES)
                .withParam(LOCALE, Locale.getDefault())
                .withParam(FILTER, filter);

        String json = wrap(() -> mapper.writeValueAsString(request));

        return connection.execute(json)
                .thenApply(body -> wrap(() -> mapper.readValue(body, EventTypeResultContainer.class)));
    }
}
