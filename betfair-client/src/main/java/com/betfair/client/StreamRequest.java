package com.betfair.client;

import com.betfair.aping.entities.MarketFilter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Value;

import java.util.Set;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "op"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StreamRequest.Authentication.class, name = "authentication"),
        @JsonSubTypes.Type(value = StreamRequest.MarketSubscription.class, name = "marketSubscription")
})
public interface StreamRequest {

    int getId();

    @Value
    public static class Authentication implements StreamRequest {

        private final int id;

        private final String session;

        private final String appKey;
    }

    @Value
    public static class MarketSubscription implements StreamRequest {

        private final int id;

        private final MarketFilter marketFilter;

        private final MarketDataFilter marketDataFilter;
    }

    @Value
    class MarketDataFilter {

        private final Set<Object> fields;

        private final int ladderLevels;
    }
}
