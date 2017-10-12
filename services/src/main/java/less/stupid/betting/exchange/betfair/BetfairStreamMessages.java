package less.stupid.betting.exchange.betfair;

import lombok.Value;

public class BetfairStreamMessages {

    @Value
    public static class ConnectionRequest {}

    @Value
    public static class ConnectionResponse {

        private final String op;

        private final String connectionId;
    }

    @Value
    public static class AuthenticationRequest {

        private final long id;

        private final String op;

        private final String appKey;

        private final String session;
    }

    @Value
    public static class AuthenticationResponse {

        private final String op;

        private final long id;

        private final String statusCode;

        private final boolean connectionClosed;
    }
}
