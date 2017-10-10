package less.stupid.betting.exchange;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class PriceAndMatchedAmount {

    private final BigDecimal price;

    private final BigDecimal matchedAmount;
}
