package less.stupid.betting.exchange.betfair;

import java.util.concurrent.CompletionStage;

public interface BetfairConnection {

    CompletionStage<String> connect();

    CompletionStage<String> execute(String body);
}
