package less.stupid.betting.exchange.betfair;

import java.util.concurrent.atomic.AtomicLong;

public class DefaultStreamRequestFactory {

    private final AtomicLong counter = new AtomicLong();

    StreamRequest authentication(String sessionToken, String appKey) {
        return new StreamRequest.Authentication(counter.incrementAndGet(), sessionToken, appKey);
    }
}
