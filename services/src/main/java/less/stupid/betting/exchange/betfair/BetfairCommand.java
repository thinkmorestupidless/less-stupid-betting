package less.stupid.betting.exchange.betfair;

import com.betfair.aping.entities.MarketFilter;
import com.betfair.aping.enums.MarketProjection;
import com.betfair.aping.enums.MarketSort;
import lombok.Value;

import java.util.Set;

public interface BetfairCommand {

    @Value
    class ListEvents {

        private final MarketFilter filter;
    }

    @Value
    class ListMarketCatalogue {

        private final MarketFilter filter;

        private final Set<MarketProjection> marketProjection;

        private final MarketSort sort;

        private final int maxResults;
    }
}
