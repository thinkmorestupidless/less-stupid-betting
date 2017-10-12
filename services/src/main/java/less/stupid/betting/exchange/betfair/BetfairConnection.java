package less.stupid.betting.exchange.betfair;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.io.File;
import java.util.concurrent.CompletionStage;

import static org.asynchttpclient.Dsl.asyncHttpClient;

public class BetfairConnection {

    private static final Logger log = LoggerFactory.getLogger(BetfairConnection.class);

    private final Config conf;

    private AsyncHttpClient httpClient;

    public BetfairConnection(Config conf) {
        this.conf = conf;
    }

    public SslContext sslContext() {
        try {
            return SslContextBuilder
                                .forClient()
                                .keyManager(new File(conf.getString("betfair.certFile")),
                                            new File(conf.getString("betfair.keyFile")),
                                            conf.getString("betfair.keyPassword"))
                                .build();
        } catch (SSLException e) {
            throw new RuntimeException("Failed to create SSL context", e);
        }
    }

    public AsyncHttpClient httpClient() {
        if (httpClient == null) {
            httpClient = asyncHttpClient(new DefaultAsyncHttpClientConfig.Builder()
                    .setSslContext(sslContext())
                    .setAcceptAnyCertificate(true));
        }

        return httpClient;
    }

    public CompletionStage<String> connect() {
        return httpClient()
                .preparePost("https://identitysso.betfair.com/api/certlogin")
                .setHeader("X-Application",conf.getString("betfair.applicationKey"))
                .addFormParam("username", conf.getString("betfair.username"))
                .addFormParam("password", conf.getString("betfair.password"))
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody);
    }
}
