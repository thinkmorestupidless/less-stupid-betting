package less.stupid.betting.strategy;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class StrategyState {

    private final String strategyId;

    private final boolean active;

    private final List<StrategyExecution> executions;

    public static StrategyState create(String strategyId) {
        return new StrategyState(strategyId, false, new ArrayList<>());
    }

    public StrategyState withActive(boolean active) {
        return new StrategyState(strategyId, active, executions);
    }

    public StrategyState add(StrategyExecution execution) {
        List<StrategyExecution> executions = new ArrayList<>(this.executions);
        executions.add(execution);

        return new StrategyState(strategyId, active, executions);
    }
}
