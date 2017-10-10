package less.stupid.betting.strategy;

import lombok.Value;

import java.io.Serializable;

@Value
public class StrategyExecution implements Serializable {

    private final long timestamp;
}
