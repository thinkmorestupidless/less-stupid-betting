package less.stupid.betting.strategy;

import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

public interface StrategyCommand extends Serializable {

    @Value
    class CreateStrategy implements StrategyCommand {}

    @Value
    class ActivateStrategy implements StrategyCommand {

        private String strategyId;
    }

    @Value
    class DeactivateStrategy implements StrategyCommand {

        private String strategyId;
    }

    @Value
    class ExecuteStrategy implements StrategyCommand {

        private String strategyId;
    }
}
