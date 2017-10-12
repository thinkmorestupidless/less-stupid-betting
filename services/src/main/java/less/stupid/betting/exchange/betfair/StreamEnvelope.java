package less.stupid.betting.exchange.betfair;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class StreamEnvelope {

    private final StreamRequest request;

    private CompletableFuture<StreamResponse> future;

    public StreamEnvelope(StreamRequest request) {
        this.request = request;
    }

    public StreamRequest getRequest() {
        return request;
    }

    public CompletableFuture<StreamResponse> getFuture() {
        if (future == null) {
            future = new CompletableFuture<>();
        }

        return future;
    }
}
