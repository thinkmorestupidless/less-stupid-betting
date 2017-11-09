package less.stupid.betting.exchange.betfair;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Value;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "op"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StreamResponse.Connection.class, name = "connection"),
        @JsonSubTypes.Type(value = StreamResponse.Status.class, name = "status"),
        @JsonSubTypes.Type(value = io.swagger.client.model.MarketChangeMessage.class, name = "mcm")
})
public interface StreamResponse {

    long getId();

    @Value
    public static class Connection implements StreamResponse {

        public long getId() {
            throw new RuntimeException("Connection response does not contain an ID");
        }

        private final String op;

        private final String connectionId;
    }

    @Value
    public static class Status implements StreamResponse {

        private final String op;

        private final long id;

        private final String statusCode;

        private final boolean connectionClosed;
    }
}
