package less.stupid.betting.exchange.betfair;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BetfairClient implements SessionProvider {

    private final Config conf = ConfigFactory.load();

    private BetfairApi api;

    private BetfairStream stream;

    private BetfairSession session = BetfairSession.loggedOut();

    public BetfairClient() {
        BetfairConnection connection = new BetfairConnection(conf);

        api = new BetfairApi(connection);
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
}
