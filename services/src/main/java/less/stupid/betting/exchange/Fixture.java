package less.stupid.betting.exchange;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.persistence.AbstractPersistentActor;
import akka.persistence.RecoveryCompleted;

public class Fixture extends AbstractPersistentActor {

    public static Props props() {
        return Props.create(Fixture.class, Fixture::new);
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private FixtureState state;

    @Override
    public String persistenceId() {
        return getSelf().path().name();
    }

    @Override
    public Receive createReceiveRecover() {
        return receiveBuilder()
                .match(FixtureEvent.FixtureCreated.class, evt -> state = FixtureState.create())
                .match(FixtureEvent.EventCancelled.class, evt -> state = state.cancelled(evt.getReason()))
                .match(FixtureEvent.EventPostponed.class, evt -> state = state.postponed(evt.getNewStartTime(), evt.getReason()))
                .match(RecoveryCompleted.class, evt -> log.debug("recovery complete"))
                .matchAny(o -> log.info("I don't know how to recover using -> {}", o))
                .build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(FixtureCommand.CreateFixture.class, this::create)
                .match(FixtureCommand.CancelFixture.class, this::cancel)
                .match(FixtureCommand.PostponeFixture.class, this::postpone)
                .matchAny(o -> log.info("hmmm... I don't know what to do with -> {} ({})", o, (o instanceof FixtureCommand.CreateFixture)))
                .build();
    }

    public void create(FixtureCommand.CreateFixture cmd) {
        log.info("creating event");

        FixtureEvent evt = new FixtureEvent.FixtureCreated(persistenceId(), cmd.getExchangeName(), cmd.getExchangeEventId(), cmd.getName(), cmd.getStartTime());

        ActorRef sender = getSender();

        persist(evt, e -> {
           state = FixtureState.create();
           sender.tell(e, getSelf());
           getContext().getSystem().eventStream().publish(e);

           log.info("event {} created", persistenceId());
        });
    }

    public void cancel(FixtureCommand.CancelFixture cmd) {
        log.info("cancelling event {}", cmd.getEventId());

        FixtureEvent evt = new FixtureEvent.EventCancelled(persistenceId(), cmd.getExchangeName(), cmd.getExchangeEventId(), cmd.getReason());

        ActorRef sender = getSender();

        persist(evt, e -> {
           state = state.cancelled(cmd.getReason());
           sender.tell(e, getSelf());
           getContext().getSystem().eventStream().publish(e);

           log.info("event {} cancelled", persistenceId());
        });
    }

    public void postpone(FixtureCommand.PostponeFixture cmd) {
        log.info("postponing event {}", cmd.getEventId());

        FixtureEvent evt = new FixtureEvent.EventPostponed(persistenceId(), cmd.getExchangeName(), cmd.getExchangeEventId(), cmd.getReason(), state.getStartTime(), cmd.getStartTime());

        ActorRef sender = getSender();

        persist(evt, e -> {
           state = state.postponed(cmd.getStartTime(), cmd.getReason());
           sender.tell(e, getSelf());
           getContext().getSystem().eventStream().publish(e);

           log.info("event {} postponed", persistenceId());
        });
    }
}
