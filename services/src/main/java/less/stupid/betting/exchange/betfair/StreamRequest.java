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
        @JsonSubTypes.Type(value = StreamRequest.Authentication.class, name = "authentication")
})
public interface StreamRequest {

    long getId();

    @Value
    public static class Authentication implements StreamRequest {

        private final long id;

        private final String session;

        private final String appKey;
    }
}
