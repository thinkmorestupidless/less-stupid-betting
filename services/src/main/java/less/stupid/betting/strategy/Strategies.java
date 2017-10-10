package less.stupid.betting.strategy;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.sharding.ClusterSharding;
import akka.cluster.sharding.ClusterShardingSettings;
import akka.cluster.sharding.ShardRegion;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Option;

import java.util.UUID;

public class Strategies extends AbstractActor {

    public static Props props() {
        return Props.create(Strategies.class, () -> new Strategies());
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final ActorRef strategy;

    static ShardRegion.MessageExtractor messageExtractor = new ShardRegion.HashCodeMessageExtractor(100) {

        @Override
        public String entityId(Object message) {

            if (message instanceof StrategyCommand.ActivateStrategy) {
                return ((StrategyCommand.ActivateStrategy) message).getStrategyId();
            }

            if (message instanceof StrategyCommand.DeactivateStrategy) {
                return ((StrategyCommand.DeactivateStrategy) message).getStrategyId();
            }

            if (message instanceof StrategyCommand.ExecuteStrategy) {
                return ((StrategyCommand.ExecuteStrategy) message).getStrategyId();
            }

            return createId(UUID.randomUUID().toString());
        }

        private String createId(String id) {
            return String.format("strategy-%s", id);
        }
    };

    public Strategies() {
        ActorSystem system = getContext().getSystem();
        Option<String> roleOption = Option.none();
        ClusterShardingSettings settings = ClusterShardingSettings.create(system);

        strategy = ClusterSharding.get(system)
                .start(
                        "Strategy",
                        Strategy.props(),
                        settings,
                        messageExtractor);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StrategyCommand.class, cmd -> strategy.tell(cmd, getSender()))
                .matchAny(o -> log.warning("i don't know what to do with {}", o))
                .build();
    }
}
