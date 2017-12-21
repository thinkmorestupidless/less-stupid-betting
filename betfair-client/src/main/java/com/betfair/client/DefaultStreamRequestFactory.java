package com.betfair.client;

import com.betfair.client.StreamRequest;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultStreamRequestFactory {

    private final AtomicInteger counter = new AtomicInteger();

    StreamRequest authentication(String sessionToken, String appKey) {
        return new StreamRequest.Authentication(counter.incrementAndGet(), sessionToken, appKey);
    }
}
