package less.stupid.betting.exchange;

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

public class Fixtures extends AbstractActor {

    public static Props props() {
        return Props.create(Fixtures.class, Fixtures::new);
    }

    static ShardRegion.MessageExtractor messageExtractor = new ShardRegion.HashCodeMessageExtractor(100) {

        @Override
        public String entityId(Object message) {

            if (message instanceof FixtureCommand.CancelFixture) {
                return ((FixtureCommand.CancelFixture) message).getEventId();
            }

            if (message instanceof FixtureCommand.PostponeFixture) {
                return ((FixtureCommand.PostponeFixture) message).getEventId();
            }

            return String.format("event-%s", UUID.randomUUID().toString());
        }
    };

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef eventRegion;

    public Fixtures() {
        ActorSystem system = getContext().getSystem();
        Option<String> roleOption = Option.none();
        ClusterShardingSettings settings = ClusterShardingSettings.create(system);

        eventRegion = ClusterSharding.get(system)
                        .start(
                            "Fixture",
                            Fixture.props(),
                            settings,
                            messageExtractor);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(FixtureCommand.class, cmd -> eventRegion.forward(cmd, getContext()))
                .matchAny(o -> log.info("I don't know how to handle -> {}", o))
                .build();
    }
}
