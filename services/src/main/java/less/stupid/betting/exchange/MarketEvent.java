package less.stupid.betting.exchange;

import lombok.Value;

import java.io.Serializable;

public interface MarketEvent extends Serializable {

    String getMarketId();

    @Value
    class MarketCreated implements MarketEvent {

        private final String marketId;

        private final String eventId;

        private final String marketType;

        private final String name;
    }
}
