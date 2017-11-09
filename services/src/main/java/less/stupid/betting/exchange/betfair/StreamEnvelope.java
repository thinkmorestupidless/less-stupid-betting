package less.stupid.betting.exchange.betfair;

import less.stupid.betting.exchange.betfair.api.exchange.stream.ResponseMessage;

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
