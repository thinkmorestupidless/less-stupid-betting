package less.stupid.betting.exchange.betfair;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionStage;

import static com.opengamma.strata.collect.Unchecked.wrap;

public class BetfairApi {

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
}
