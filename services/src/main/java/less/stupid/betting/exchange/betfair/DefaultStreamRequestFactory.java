package less.stupid.betting.exchange.betfair;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultStreamRequestFactory {

    private final AtomicInteger counter = new AtomicInteger();

    StreamRequest authentication(String sessionToken, String appKey) {
        return new StreamRequest.Authentication(counter.incrementAndGet(), sessionToken, appKey);
    }
}
