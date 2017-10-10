package less.stupid.betting.exchange;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.persistence.cassandra.query.javadsl.CassandraReadJournal;
import akka.persistence.query.EventEnvelope;
import akka.persistence.query.Offset;
import akka.persistence.query.PersistenceQuery;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import com.datastax.driver.core.PreparedStatement;
import less.stupid.cassandra.CassandraSession;

import java.util.UUID;

public class ExchangeReadSideEventProcessor extends AbstractActor {

    public static Props props(CassandraSession session) {
        return Props.create(ExchangeReadSideEventProcessor.class, () -> new ExchangeReadSideEventProcessor(session));
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final CassandraSession session;

    private PreparedStatement insertEventStatement;

    public ExchangeReadSideEventProcessor(CassandraSession session) {
        this.session = session;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ReadSideProtocol.Start.class, this::start)
                .match(ReadSideProtocol.RegisterForEvents.class, this::registerForEvents)
                .build();
    }

    public void start(ReadSideProtocol.Start cmd) {
        prepareStatements();
    }

    public void prepareStatements() {
        session.prepare("INSERT INTO betting.events (eventId, exchangeEventId, exchangeName, name, startTime) VALUES (?,?,?,?,?)")
                .exceptionally(t -> {
                    log.error("failed to create insertEventStatement -> {}", t);
                    return null;
                })
                .thenAccept(statement -> {
                    this.insertEventStatement = statement;
                    getSelf().tell(new ReadSideProtocol.RegisterForEvents(), getSelf());
                });
    }

    public void registerForEvents(ReadSideProtocol.RegisterForEvents cmd) {
        log.debug("registering for events");

        Materializer materializer = ActorMaterializer.create(getContext().getSystem());

        CassandraReadJournal journal = PersistenceQuery.get(getContext().getSystem())
                        .getReadJournalFor(CassandraReadJournal.class, CassandraReadJournal.Identifier());

        journal.eventsByTag("event", Offset.noOffset()).runForeach(this::handleEvent, materializer);
    }

    public void handleEvent(EventEnvelope evt) {
        log.debug("handling event {}", evt.event());

        if (evt.event() instanceof FixtureEvent.FixtureCreated) {
            createEvent((FixtureEvent.FixtureCreated) evt.event());
        }
    }

    public void createEvent(FixtureEvent.FixtureCreated evt) {
        log.debug("creating event -> {}", evt);

        session.execute(insertEventStatement.bind(cleanEventId(evt.getEventId()), evt.getExchangeEventId(), evt.getExchangeName(), evt.getEventName(), evt.getStartTime()));
    }

    public UUID cleanEventId(String eventId) {
        if (eventId.startsWith("event-")) {
            return UUID.fromString(eventId.substring("event-".length()));
        }

        return UUID.fromString(eventId);
    }
}
