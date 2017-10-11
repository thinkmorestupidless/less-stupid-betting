package less.stupid.kafka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import less.stupid.betting.exchange.FixtureEvent;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KafkaConsumerActor extends AbstractActor {

    public static Props props() {
        return Props.create(KafkaConsumerActor.class, KafkaConsumerActor::new);
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private final ObjectMapper mapper = new ObjectMapper();

    private final Map<String, Set<ActorRef>> subscribers = Maps.newHashMap();

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private Consumer<Long, String> consumer;

    private Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleProducer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Create the consumer using props.
        final Consumer<Long, String> consumer = new KafkaConsumer<Long, String>(props);

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList("fixtures"));
        return consumer;
    }

    @Override
    public void preStart() throws Exception {
        consumer = createConsumer();

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        ConsumerRecords<Long, String> records = consumer.poll(Long.MAX_VALUE);

                        for (ConsumerRecord<Long, String> record : records) {
                            Map<String, Object> data = Maps.newHashMap();
                            data.put("partition", record.partition());
                            data.put("offset", record.offset());
                            data.put("value", record.value());

                            if (record.topic().equals("fixtures")) {
                                try {
                                    FixtureEvent event = mapper.readValue(record.value(), FixtureEvent.class);

                                    log.info("event -> {}", event);
                                } catch (IOException e) {
                                    log.error("failed to deserialize json -> {}", e);
                                }
                            }

                            log.info("data -> {}", data);


                        }
                    }
                } catch (WakeupException e) {
                    log.warning("Kafka shutdown");
                } finally {
                    consumer.close();
                }
            }
        });
    }

    @Override
    public void postStop() throws Exception {
        consumer.wakeup();
        executor.shutdown();
        try {
            executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.postStop();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(KafkaProtocol.SubscribeToTopic.class, this::subscribe)
                .build();
    }

    public void subscribe(KafkaProtocol.SubscribeToTopic subscription) {
        if (!subscribers.containsKey(subscription.getTopic())) {
            subscribers.put(subscription.getTopic(), Sets.newHashSet());
        }

        subscribers.get(subscription.getTopic()).add(getSender());
    }
}
