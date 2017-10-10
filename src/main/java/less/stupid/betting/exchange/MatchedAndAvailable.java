package less.stupid.betting.exchange;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class MatchedAndAvailable implements Serializable {

    public static final MatchedAndAvailable None = new MatchedAndAvailable(BigDecimal.ZERO, BigDecimal.ZERO);

    private final BigDecimal matched;

    private final BigDecimal available;

    public MatchedAndAvailable addAvailable(BigDecimal available) {
        return new MatchedAndAvailable(matched, this.available.add(available));
    }
}
