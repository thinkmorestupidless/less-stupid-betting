package less.stupid.betting.exchange;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ExchangeCommandHandler extends AbstractActor {

    public static Props props(ActorRef events) {
        return Props.create(ExchangeCommandHandler.class, () -> new ExchangeCommandHandler(events));
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final ActorRef events;

    public ExchangeCommandHandler(ActorRef events) {
        this.events = events;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(FixtureCommand.class, cmd -> events.forward(cmd, getContext()))
                .build();
    }
}
