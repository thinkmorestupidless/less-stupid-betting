package less.stupid.betting.strategy;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.persistence.AbstractPersistentActor;
import akka.persistence.RecoveryCompleted;

public class Strategy extends AbstractPersistentActor {

    public static Props props() {
        return Props.create(Strategy.class, () -> new Strategy());
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private StrategyState state;

    public Strategy() {

    }

    @Override
    public String persistenceId() {
        return getSelf().path().name();
    }

    @Override
    public Receive createReceiveRecover() {
        return receiveBuilder()
                .match(StrategyEvent.StrategyCreated.class, evt -> state = StrategyState.create(persistenceId()))
                .match(StrategyEvent.StrategyActivated.class, evt -> state = state.withActive(true))
                .match(StrategyEvent.StrategyDeactivated.class, evt -> state = state.withActive(false))
                .match(StrategyEvent.StrategyExecuted.class, evt -> state = state.add(evt.getExecution()))
                .matchAny(o -> log.warning("I don't know what to do with {}", o))
                .build();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StrategyCommand.CreateStrategy.class, this::create)
                .match(StrategyCommand.ActivateStrategy.class, this::activate)
                .match(StrategyCommand.DeactivateStrategy.class, this::deactivate)
                .match(StrategyCommand.ExecuteStrategy.class, this::execute)
                .matchAny(o -> log.warning("I don't know what to do with {}", o))
                .build();
    }

    public void create(StrategyCommand.CreateStrategy cmd) {
        log.info("creating strategy -> {}", persistenceId());

        StrategyEvent evt = new StrategyEvent.StrategyCreated(persistenceId());

        ActorRef sender = getSender();

        persist(evt, e -> {
            state = StrategyState.create(persistenceId());
            sender.tell(e, getSelf());
            getContext().getSystem().eventStream().publish(e);

            log.info("strategy {} created", persistenceId());
        });
    }

    public void activate(StrategyCommand.ActivateStrategy cmd) {
        log.info("activating strategy -> {}", persistenceId());

        StrategyEvent evt = new StrategyEvent.StrategyActivated(persistenceId());

        ActorRef sender = getSender();

        persist(evt, e -> {
            state = state.withActive(true);
            sender.tell(e, getSelf());
            getContext().getSystem().eventStream().publish(e);

            log.info("strategy {} activated", persistenceId());
        });
    }

    public void deactivate(StrategyCommand.DeactivateStrategy cmd) {
        log.info("deactivating strategy -> {}", persistenceId());

        StrategyEvent evt = new StrategyEvent.StrategyDeactivated(persistenceId());

        ActorRef sender = getSender();

        persist(evt, e -> {
            state = state.withActive(false);
            sender.tell(e, getSelf());
            getContext().getSystem().eventStream().publish(e);

            log.info("strategy {} deactivated", persistenceId());
        });
    }

    public void execute(StrategyCommand.ExecuteStrategy cmd) {
        log.info("executing strategy -> {}", persistenceId());

        if (state.isActive()) {

            StrategyExecution execution = new StrategyExecution(System.currentTimeMillis());
            StrategyEvent evt = new StrategyEvent.StrategyExecuted(persistenceId(), execution);

            ActorRef sender = getSender();

            persist(evt, e -> {
                state = state.add(execution);
                sender.tell(e, getSelf());
                getContext().getSystem().eventStream().publish(e);

                log.info("strategy {} executed", persistenceId());
            });

        } else {
            log.info("not executing strategy {} - strategy is not active", persistenceId());
        }
    }
}
