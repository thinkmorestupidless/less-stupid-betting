package com.betfair.client;

import akka.Done;
import akka.NotUsed;
import akka.stream.Materializer;
import akka.stream.OverflowStrategy;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.betfair.stream.model.ResponseMessage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class StreamEnvelope {

    private final StreamRequest request;

    private CompletableFuture<ResponseMessage> future;

    public StreamEnvelope(StreamRequest request) {
        this.request = request;
    }

    public StreamRequest getRequest() {
        return request;
    }

    public CompletionStage<Done> forEach(Flow<Object, Done, NotUsed> flow) {
        Materializer mat = null;
        return Source.queue(100, OverflowStrategy.backpressure()).via(flow).to(Sink.ignore()).run(mat);
    }

    public CompletableFuture<ResponseMessage> getFuture() {
        if (future == null) {
            future = new CompletableFuture<>();
        }

        return future;
    }

    @Override
    public String toString() {
        return "StreamEnvelope{" +
                "request=" + request +
                ", future=" + future +
                '}';
    }
}
