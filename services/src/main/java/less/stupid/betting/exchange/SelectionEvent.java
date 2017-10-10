package less.stupid.betting.exchange;

import lombok.Value;

import java.io.Serializable;

public interface SelectionEvent extends Serializable {

    String getSelectionId();

    @Value
    class SelectionCreated implements SelectionEvent {

        private final String exchangeMarketId;

        private final String exchangeName;

        private final String selectionId;
    }

    @Value
    class SelectionPricesChanged implements SelectionEvent {

        private final String exchangeMarketId;

        private final String exchangeName;

        private final String selectionId;

        private final PriceLadder oldPrices;

        private final PriceLadder newPrices;
    }
}
