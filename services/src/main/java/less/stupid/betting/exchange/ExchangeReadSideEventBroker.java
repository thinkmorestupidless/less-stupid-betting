package less.stupid.betting.exchange;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.persistence.cassandra.query.javadsl.CassandraReadJournal;
import akka.persistence.query.EventEnvelope;
import akka.persistence.query.Offset;
import akka.persistence.query.PersistenceQuery;
import akka.stream.ActorMaterializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import less.stupid.kafka.KafkaActor;
import less.stupid.kafka.KafkaProtocol;

import static com.opengamma.strata.collect.Unchecked.wrap;

public class ExchangeReadSideEventBroker extends AbstractActor {

    public static Props props() {
        return Props.create(ExchangeReadSideEventBroker.class, ExchangeReadSideEventBroker::new);
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private ActorRef kafka;

    private final ObjectMapper mapper;

    public ExchangeReadSideEventBroker() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ReadSideProtocol.Start.class, this::start)
                .build();
    }

    public void start(ReadSideProtocol.Start cmd) {
        kafka = getContext().actorOf(KafkaActor.props());

        PersistenceQuery.get(getContext().getSystem())
                .getReadJournalFor(CassandraReadJournal.class, CassandraReadJournal.Identifier())
                .eventsByTag("event", Offset.noOffset()).runForeach(this::handleEvent, ActorMaterializer.create(getContext().getSystem()));
    }

    public void handleEvent(EventEnvelope env) {
        log.info("sending message to kafka -> {}", env.event());
        kafka.tell(new KafkaProtocol.SendMessage("fixtures", 0L, wrap(() -> mapper.writeValueAsString(env.event()))), getSelf());
    }
}
