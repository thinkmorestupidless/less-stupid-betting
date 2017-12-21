package less.stupid.betting.exchange;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpHeader;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.headers.ModeledCustomHeader;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import com.betfair.client.BetfairConnection;
import com.betfair.client.SessionProvider;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionStage;

public class AkkaHttpBetfairConnection implements BetfairConnection {

    private static final Logger log = LoggerFactory.getLogger(AkkaHttpBetfairConnection.class);

    private final SessionProvider sessions;

    private final Config conf;

    private final Http http;

    private final Materializer materializer;

    public AkkaHttpBetfairConnection(SessionProvider sessions, ActorSystem system, Config conf) {
        this.sessions = sessions;
        this.conf = conf;
        http = Http.get(system);
        materializer = ActorMaterializer.create(system);
    }

    @Override
    public CompletionStage<String> connect() {

//        .preparePost("https://identitysso.betfair.com/api/certlogin")
//                .setHeader("X-Application",conf.getString("betfair.applicationKey"))
//                .addFormParam("username", conf.getString("betfair.username"))
//                .addFormParam("password", conf.getString("betfair.password"))
//                .execute()
//                .toCompletableFuture()
//                .thenApply(Response::getResponseBody);
        HttpHeader appKey = new ModeledCustomHeader("X-Application", conf.getString("betfair.applicationKey")) {
            @Override
            public boolean renderInRequests() {
                return true;
            }

            @Override
            public boolean renderInResponses() {
                return true;
            }
        };

        return http.singleRequest(HttpRequest.POST("https://identitysso.betfair.com/api/certlogin").addHeader(appKey), materializer)
                .exceptionally(t -> {
                    System.out.println("request failed -> " + t);
                    return null;
                }).thenApply(response -> {
                    System.out.println("request succeeded -> " + response);
            return response.entity().toString();
        });
    }

    @Override
    public CompletionStage<String> execute(String body) {
//        return sessions.session()
//                .thenCompose(session -> httpClient()
//                        .preparePost("https://api.betfair.com/exchange/betting/json-rpc/v1")
//                        .setBody(body)
//                        .setHeader("Content-Type", "application/json")
//                        .setHeader("Accept", "application/json")
//                        .setHeader("Accept-Charset", "UTF-8")
//                        .setHeader("Accept-Encoding", "gzip, deflate")
//                        .setHeader("X-Application",conf.getString("betfair.applicationKey"))
//                        .setHeader("X-Authentication", session.sessionToken())
//                        .execute()
//                        .toCompletableFuture()
//                        .thenApply(Response::getResponseBody));

        return sessions.session()
                .thenCompose(session -> http.singleRequest(HttpRequest.POST("https://api.betfair.com/exchange/betting/json-rpc/v1"), materializer).thenApply(response -> {
                    response.discardEntityBytes(materializer);
                    return "completed";
                }));
    }
}
