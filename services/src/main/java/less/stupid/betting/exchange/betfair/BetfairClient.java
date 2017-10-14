package less.stupid.betting.exchange.betfair;

import akka.Done;
import com.betfair.aping.entities.Event;
import com.betfair.aping.entities.EventResult;
import com.betfair.aping.entities.EventTypeResult;
import com.betfair.aping.entities.MarketFilter;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BetfairClient implements SessionProvider {

    private static final Logger log = LoggerFactory.getLogger(BetfairClient.class);

    private final Config conf = ConfigFactory.load();

    private BetfairApi api;

    private BetfairStream stream;

    private BetfairSession session = BetfairSession.loggedOut();

    public BetfairClient() {
        BetfairConnection connection = new BetfairConnection(this, conf);

        api = new BetfairApi(new BetfairConnection(this, conf));
        stream = new BetfairStream(this, new DefaultStreamRequestFactory(), conf);
    }

    public CompletionStage<Done> stream() {
        return stream.connect();
    }

    @Override
    public CompletionStage<BetfairSession> session() {
        if (session.isLoggedIn()) {
            return CompletableFuture.completedFuture(session);
        }

        return login();
    }

    public CompletionStage<BetfairSession> login() {
        return api.login().thenApply(response -> {
            session = BetfairSession.loggedIn(response.getSessionToken(), conf.getString("betfair.applicationKey"));
            return session;
        });
    }

    public CompletionStage<List<EventTypeResult>> listEventTypes(MarketFilter filter) {
        log.info("listing event types");
        return api.listEventTypes(filter).thenApply(response -> response.getResult());
    }

    public CompletionStage<List<EventResult>> listEvents(MarketFilter filter) {
        log.info("listing events");
        return api.listEvents(filter).thenApply(response -> response.getResult());
    }
}
