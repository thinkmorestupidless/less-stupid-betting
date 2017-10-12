package less.stupid.betting.exchange.betfair;

public class StreamEnvelope {

    private final long id;

    private final Object request;

    private final Object response;

    public StreamEnvelope(long id, Object request, Object response) {
        this.id = id;
        this.request = request;
        this.response = response;
    }

    public StreamEnvelope(long id, Object request) {
        this(id, request, null);
    }

    public StreamEnvelope withResponse(Object response) {
        return new StreamEnvelope(id, request, response);
    }

    public long getId() {
        return id;
    }

    public Object getRequest() {
        return request;
    }

    public Object getResponse() {
        return response;
    }
}
