package less.stupid.betting.strategy;

import lombok.Value;

import java.io.Serializable;

public interface StrategyEvent extends Serializable {

    String getStrategyId();

    @Value
    public class StrategyCreated implements StrategyEvent {

        private final String strategyId;
    }

    @Value
    public class StrategyActivated implements StrategyEvent {

        private final String strategyId;
    }

    @Value
    public class StrategyDeactivated implements StrategyEvent {

        private final String strategyId;
    }

    @Value
    class StrategyExecuted implements StrategyEvent {

        private final String strategyId;

        private final StrategyExecution execution;
    }
}
