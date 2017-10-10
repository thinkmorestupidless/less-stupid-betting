package less.stupid.betting.exchange;

import lombok.Value;

import java.io.Serializable;

public interface FixtureEvent extends Serializable {

    String getEventId();

    String getExchangeName();

    String getExchangeEventId();

    @Value
    static class FixtureCreated implements FixtureEvent {

        private final String eventId;

        private final String exchangeName;

        private final String exchangeEventId;

        private final String eventName;

        private final long startTime;
    }

    @Value
    class EventCancelled implements FixtureEvent {

        private final String eventId;

        private final String exchangeName;

        private final String exchangeEventId;

        private final String reason;
    }

    @Value
    class EventPostponed implements FixtureEvent {

        private final String eventId;

        private final String exchangeName;

        private final String exchangeEventId;

        private final String reason;

        private final long oldStartTime;

        private final long newStartTime;
    }
}
