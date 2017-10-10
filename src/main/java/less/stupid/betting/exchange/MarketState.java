package less.stupid.betting.exchange;

import com.google.common.collect.Sets;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Value
public class MarketState implements Serializable {

    public static MarketState create(UUID marketId, String exchangeMarketId, String exchangeEventId, String marketType) {
        return new MarketState(marketId, exchangeMarketId, exchangeEventId, marketType, Sets.newHashSet());
    }

    private final UUID marketId;

    private final String exchangeMarketId;

    private final String exchangeEventId;

    private final String marketType;

    private final Set<SelectionState> selections;
}
