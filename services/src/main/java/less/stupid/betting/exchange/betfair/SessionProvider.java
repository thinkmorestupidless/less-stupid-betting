package less.stupid.betting.exchange.betfair;

import java.util.concurrent.CompletionStage;

public interface SessionProvider {

    CompletionStage<BetfairSession> session();
}
