package less.stupid.betting.exchange;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Value
public class SelectionState implements Serializable {

    public enum SelectionStatus {
        RUNNER, NON_RUNNER
    }

    public static SelectionState create(UUID selectionId) {
        return new SelectionState(SelectionStatus.RUNNER, selectionId, "", new PriceLadder());
    }

    private final SelectionStatus status;

    private final UUID selectionId;

    private final String name;

    private final PriceLadder prices;

    public SelectionState withName(String name) {
        return new SelectionState(status, selectionId, name, prices);
    }

    public SelectionState nonRunner() {
        return new SelectionState(SelectionStatus.NON_RUNNER, selectionId, name, prices);
    }
}
