package less.stupid.betting.exchange;

import com.google.common.collect.Maps;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Value
public class PriceLadder implements Serializable {

    private final Map<Side, Map<BigDecimal, MatchedAndAvailable>> matchedAndAvailableByPriceBySide = Maps.newHashMap();

    public MatchedAndAvailable forPriceAndSide(BigDecimal price, Side side) {
        if (!matchedAndAvailableByPriceBySide.containsKey(side)) {
            matchedAndAvailableByPriceBySide.put(side, Maps.newHashMap());
        }

        Map<BigDecimal, MatchedAndAvailable> matchedAndAvailableByPrice = matchedAndAvailableByPriceBySide.get(side);

        if (matchedAndAvailableByPrice.containsKey(price)) {
            return matchedAndAvailableByPrice.get(price);
        }

        return MatchedAndAvailable.None;
    }

    public void setMatchedAndAvailableAtPriceAndSide(MatchedAndAvailable mAndA, BigDecimal price, Side side) {
        if (!matchedAndAvailableByPriceBySide.containsKey(side)) {
            matchedAndAvailableByPriceBySide.put(side, Maps.newHashMap());
        }

        Map<BigDecimal, MatchedAndAvailable> matchedAndAvailableByPrice = matchedAndAvailableByPriceBySide.get(side);
        matchedAndAvailableByPrice.put(price, mAndA);
    }
}
