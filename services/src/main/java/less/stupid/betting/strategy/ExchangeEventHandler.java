package less.stupid.betting.strategy;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import less.stupid.betting.exchange.FixtureEvent;
import less.stupid.kafka.KafkaProtocol;

public class ExchangeEventHandler extends AbstractActor {

    public static Props props(ActorRef broker) {
        return Props.create(ExchangeEventHandler.class, () -> new ExchangeEventHandler(broker));
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final ActorRef broker;

    public ExchangeEventHandler(ActorRef broker) {
        this.broker = broker;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StrategyProtocol.Start.class, this::start)
                .match(FixtureEvent.class, this::fixtureEvent)
                .build();
    }

    public void start(StrategyProtocol.Start s) {
        broker.tell(new KafkaProtocol.SubscribeToTopic("fixtures"), getSelf());
    }

    public void fixtureEvent(FixtureEvent e) {
        log.info("received fixture event -> {}", e);
    }
}
