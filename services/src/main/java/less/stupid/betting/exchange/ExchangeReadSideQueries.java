package less.stupid.betting.exchange;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import less.stupid.cassandra.CassandraSession;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangeReadSideQueries extends AbstractActor {

    public static Props props(CassandraSession session) {
        return Props.create(ExchangeReadSideQueries.class, () -> new ExchangeReadSideQueries(session));
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final CassandraSession session;

    public ExchangeReadSideQueries(CassandraSession session) {
        this.session = session;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ReadSideProtocol.ListEvents.class, this::listEvents)
                .build();
    }

    public void listEvents(ReadSideProtocol.ListEvents query) {
        ActorRef sender = getSender();

        session.execute("SELECT * FROM betting.events")
                .exceptionally(t -> {
                    log.warning("failed to retrieve events -> {}", t);
                    return Collections.emptyList();
                })
                .thenAccept(result -> {
                    List<XEvent> events = result.stream()
                            .map(XEvent::create)
                            .sorted(XEvent.ascendingStartTime)
                            .collect(Collectors.toList());

                    sender.tell(events, getSelf());
                });
    }
}
