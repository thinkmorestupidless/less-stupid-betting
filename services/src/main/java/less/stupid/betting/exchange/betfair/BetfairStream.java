package less.stupid.betting.exchange.betfair;

import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetfairStream {

    private static final Logger log = LoggerFactory.getLogger(BetfairStream.class);

    private StreamSocket socket;

    public BetfairStream(SessionProvider sessions, Config config) {
        socket = new StreamSocket(sessions, config);
    }

    public void authenticate() {
        socket.authenticate().thenAccept(done -> log.info("Authentication is done")).toCompletableFuture().join();
    }
}
