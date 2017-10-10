package less.stupid.betting.exchange;

import lombok.Value;

import java.io.Serializable;

public interface MarketCommand extends Serializable {

    @Value
    class CreateMarket implements MarketCommand {

        private final String exchangeEventId;

        private final String exchangeMarketId;

        private final String marketType;

        private final String name;
    }
}
