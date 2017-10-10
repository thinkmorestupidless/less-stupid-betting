package less.stupid.kafka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KafkaClientActor extends AbstractActor {

    public static Props props() {
        return Props.create(KafkaClientActor.class, KafkaClientActor::new);
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private Producer<Long, String> producer;

    private Producer<Long, String> createProducer() {
        Properties props = new Properties();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<>(props);
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();

        producer = createProducer();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(KafkaMessage.SendMessage.class, this::sendMessage)
                .matchAny(o -> log.info("i don't know what to do with -> {}", o))
                .build();
    }

    public void sendMessage(KafkaMessage.SendMessage m) {
        log.info("sending message -> {}", m);

        final ActorRef sender = getSender();
        final ProducerRecord<Long, String> record = new ProducerRecord<>(m.getTopic(), m.getKey(), m.getMessage());
        producer.send(record, (metadata, exception) -> {
            if (metadata != null) {
                log.info("sent record(key={} value={}) meta(partition={}, offset={})", record.key(), record.value(), metadata.partition(), metadata.offset());
            } else {
                exception.printStackTrace();
            }
        });
    }
}
