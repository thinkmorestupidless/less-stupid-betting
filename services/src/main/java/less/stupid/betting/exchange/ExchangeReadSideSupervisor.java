package less.stupid.betting.exchange;

import akka.actor.AbstractActorWithStash;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import less.stupid.cassandra.CassandraSession;
import less.stupid.cassandra.CassandraSessionImpl;

import java.util.Collections;

public class ExchangeReadSideSupervisor extends AbstractActorWithStash {

    public static Props props() {
        return Props.create(ExchangeReadSideSupervisor.class, ExchangeReadSideSupervisor::new);
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private CassandraSession session;

    private ActorRef queries;

    @Override
    public Receive createReceive() {
        return initialBehaviour;
    }

    private Receive initialBehaviour = receiveBuilder()
            .match(ReadSideProtocol.ListEvents.class, e -> stash())
            .match(ReadSideProtocol.Start.class, this::start)
            .match(ReadSideProtocol.RegisterForEvents.class, this::registerForEvents)
            .build();

    private Receive ableToHandleQueries = receiveBuilder()
            .match(ReadSideProtocol.ListEvents.class, e -> queries.forward(e, getContext()))
            .build();

    public void registerForEvents(ReadSideProtocol.RegisterForEvents cmd) {
        getContext().actorOf(ExchangeReadSideEventProcessor.props(session), "read-side-events").tell(new ReadSideProtocol.Start(), getSelf());

        queries = getContext().actorOf(ExchangeReadSideQueries.props(session), "read-side-queries");
        queries.tell(new ReadSideProtocol.Start(), getSelf());

        unstashAll();
        getContext().become(ableToHandleQueries);
    }

    public void start(ReadSideProtocol.Start cmd) {
        (session = new CassandraSessionImpl()).connect(getContext().getSystem(), getContext().dispatcher()).thenAccept(done -> prepareKeyspace());
    }

    public void prepareKeyspace() {

        session.execute("CREATE KEYSPACE IF NOT EXISTS betting " +
                                "WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'} " +
                                "AND durable_writes = true")
                .exceptionally(t -> {
                    log.error("failed to create schema -> {}", t);
                    return Collections.emptyList();
                })
                .thenAccept(result -> prepareTables());
    }

    public void prepareTables() {

        session.execute("CREATE TABLE IF NOT EXISTS betting.events (" +
                                "eventId UUID, " +
                                "name text, " +
                                "exchangeName text, " +
                                "exchangeEventId text, " +
                                "startTime timestamp, " +
                                "PRIMARY KEY (eventId)" +
                                ")")
                .exceptionally(t -> {
                    log.error("failed to create tables -> {}", t);
                    return Collections.emptyList();
                })
                .thenAccept(result -> getSelf().tell(new ReadSideProtocol.RegisterForEvents(), getSelf()));
    }
}
