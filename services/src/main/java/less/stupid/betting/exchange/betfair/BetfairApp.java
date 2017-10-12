package less.stupid.betting.exchange.betfair;

import com.typesafe.config.ConfigFactory;

public class BetfairApp {

    public static void main(String[] args) {
        BetfairClient client = new BetfairClient();
        BetfairStream stream = new BetfairStream(client, ConfigFactory.load());

        stream.authenticate();
    }
}
