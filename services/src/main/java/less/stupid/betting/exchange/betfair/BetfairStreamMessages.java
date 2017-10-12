package less.stupid.betting.exchange.betfair;

import lombok.Value;

public class BetfairStreamMessages {

    @Value
    public static class AuthenticationMessage {

        private final long id;

        private final String op;

        private final String appKey;

        private final String session;
    }
}
