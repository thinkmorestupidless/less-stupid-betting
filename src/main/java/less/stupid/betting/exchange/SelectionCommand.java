package less.stupid.betting.exchange;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

public interface SelectionCommand extends Serializable {

    @Value
    class CreateSelection implements SelectionCommand {

        private final String exchangeMarketId;

        private final String exchangeName;

        private final String selectionId;

        private final String name;
    }

    @Value
    class ChangeAmountMatchedOnSelectionAtPrice implements SelectionCommand {

        private final String exchangeMarketId;

        private final String exchangeName;

        private final String selectionId;

        private final BigDecimal price;

        private final BigDecimal amount;
    }
}
