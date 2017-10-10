package less.stupid.kafka;

import lombok.Value;

public interface KafkaMessage {

    @Value
    class SendMessage {

        private final String topic;

        private final Long key;

        private final String message;
    }
}
