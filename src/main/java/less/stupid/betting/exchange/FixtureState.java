package less.stupid.betting.exchange;

import com.google.common.collect.Sets;
import lombok.Value;

import java.util.Set;

@Value
public class FixtureState {

    public enum EventStatus {
        LIVE, COMPLETE, CANCELLED, POSTPONED
    }

    private final EventStatus status;

    private final String exchangeEventId;

    private final String exchangeName;

    private final String name;

    private final long startTime;

    private final Set<MarketState> markets;

    private final String reason;

    public static FixtureState create() {
        return new FixtureState(EventStatus.LIVE, "", "", "", 0L, Sets.newHashSet(), null);
    }

    public FixtureState withState(EventStatus status) {
        return new FixtureState(status, exchangeEventId, exchangeName, name, startTime, markets, reason);
    }

    public FixtureState withExchangeEventId(String exchangeEventId) {
        return new FixtureState(status, exchangeEventId, exchangeName, name, startTime, markets, reason);
    }

    public FixtureState withExchangeName(String exchangeName) {
        return new FixtureState(status, exchangeEventId, exchangeName, name, startTime, markets, reason);
    }

    public FixtureState withName(String name) {
        return new FixtureState(status, exchangeEventId, exchangeName, name, startTime, markets, reason);
    }

    public FixtureState withStartTime(long startTime) {
        return new FixtureState(status, exchangeEventId, exchangeName, name, startTime, markets, reason);
    }

    public FixtureState withMarkets(Set<MarketState> markets) {
        return new FixtureState(status, exchangeEventId, exchangeName, name, startTime, markets, reason);
    }

    public FixtureState withReason(String reason) {
        return new FixtureState(status, exchangeEventId, exchangeName, name, startTime, markets, reason);
    }

    public FixtureState cancelled(String reason) {
        return withState(EventStatus.CANCELLED).withReason(reason);
    }

    public FixtureState postponed(long startTime, String reason) {
        return withState(EventStatus.POSTPONED).withReason(reason);
    }


}
