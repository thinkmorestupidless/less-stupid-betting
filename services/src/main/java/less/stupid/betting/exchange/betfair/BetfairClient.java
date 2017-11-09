package less.stupid.betting.exchange.betfair;

import akka.Done;
import akka.actor.ActorSystem;
import com.betfair.aping.containers.ListMarketCatalogueContainer;
import com.betfair.aping.entities.EventResult;
import com.betfair.aping.entities.EventTypeResult;
import com.betfair.aping.entities.MarketCatalogue;
import com.betfair.aping.entities.MarketFilter;
import com.betfair.aping.enums.MarketProjection;
import com.betfair.aping.enums.MarketSort;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import less.stupid.betting.exchange.betfair.api.BetfairApi;
import less.stupid.betting.exchange.betfair.api.exchange.stream.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class BetfairClient implements SessionProvider {

    private static final Logger log = LoggerFactory.getLogger(BetfairClient.class);

    private final Config conf = ConfigFactory.load();

    private BetfairApi api;

    private BetfairStream stream;

    private BetfairSession session = BetfairSession.loggedOut();

    public BetfairClient(ActorSystem system) {
        BetfairConnection connection = new AsyncHttpBetfairConnection(this, conf);

        api = new BetfairApi(connection);
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
        return api.login().thenApply(response -> BetfairSession.loggedIn(response.getSessionToken(), conf.getString("betfair.applicationKey")));
    }

    public CompletionStage<List<EventTypeResult>> listEventTypes(MarketFilter filter) {
        return api.listEventTypes(filter).thenApply(response -> response.getResult());
    }

    public CompletionStage<List<EventResult>> listEvents(MarketFilter filter) {
        return api.listEvents(filter).thenApply(response -> response.getResult());
    }

    public CompletionStage<List<MarketCatalogue>> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection, MarketSort sort, int maxResults) {
        return api.listMarketCatalogue(filter, marketProjection, sort, maxResults).thenApply(response -> response.getResult());
    }

    public CompletionStage<ResponseMessage> subscribeToMarket(MarketFilter marketFilter, StreamRequest.MarketDataFilter marketDataFilter) {
        log.info("subscribing to market {}", marketFilter);
        return stream.subscribe(marketFilter, marketDataFilter);
    }
}
