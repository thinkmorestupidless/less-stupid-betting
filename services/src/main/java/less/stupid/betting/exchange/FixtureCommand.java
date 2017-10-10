package less.stupid.betting.exchange;

import com.google.common.collect.Sets;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

public interface FixtureCommand extends Serializable {

    static FixtureCommand create(String exchangeName, String exchangeEventId, String name, long startTime) {
        return new CreateFixture(exchangeName, exchangeEventId, name, startTime, Sets.newHashSet());
    }

    static FixtureCommand create(String exchangeName, String exchangeEventId, String name, long startTime, Set<MarketState> markets) {
        return new CreateFixture(exchangeName, exchangeEventId, name, startTime, markets);
    }

    @Value
    class CreateFixture implements FixtureCommand {

        private final String exchangeName;

        private final String exchangeEventId;

        private final String name;

        private final long startTime;

        private final Set<MarketState> markets;
    }

    @Value
    class PostponeFixture implements FixtureCommand {

        private final String eventId;

        private final String exchangeName;

        private final String exchangeEventId;

        private final long startTime;

        private final String reason;
    }

    @Value
    class CancelFixture implements FixtureCommand {

        private final String eventId;

        private final String exchangeName;

        private final String exchangeEventId;

        private final String reason;
    }
}
