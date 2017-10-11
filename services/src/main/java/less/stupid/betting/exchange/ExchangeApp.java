package less.stupid.betting.exchange;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.util.Timeout;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import scala.concurrent.duration.Duration;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static com.opengamma.strata.collect.Unchecked.wrap;

public class ExchangeApp {

    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            startup(new String[] { "3551", "3552", "0" });
        else
            startup(args);
    }

    public static void startup(String[] ports) throws Exception {
        for (String port : ports) {
            // Override the configuration of the port
            Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port)
                                         .withFallback(ConfigFactory.load());

            // Create an Akka system
            ActorSystem system = ActorSystem.create("BettingSystem", config);

            ActorRef events = system.actorOf(Fixtures.props(), "fixtures");
            ActorRef commands = system.actorOf(ExchangeCommandHandler.props(events), "exchange-command-handler");
            ActorRef exchange = system.actorOf(RandomExchange.props(commands), "random-exchange");

            ActorRef readSide = system.actorOf(ExchangeReadSideSupervisor.props(), "exchange-read-side");
            ActorRef broker = system.actorOf(ExchangeReadSideEventBroker.props(), "exchange-event-broker");

            readSide.tell(new ReadSideProtocol.Start(), ActorRef.noSender());
            broker.tell(new ReadSideProtocol.Start(), ActorRef.noSender());

            if (port.equals("0")) {
                exchange.tell(new ExchangeCommand.Start(), ActorRef.noSender());

                final MinimalHttpApp myServer = new MinimalHttpApp(readSide);
                myServer.startServer("localhost", 8080);
            }
        }
    }

    static class MinimalHttpApp extends HttpApp {

        private final ObjectMapper mapper = new ObjectMapper();

        private final ActorRef readSide;

        public MinimalHttpApp(ActorRef readSide) {
            this.readSide = readSide;
        }

        @Override
        protected Route routes() {
            return path("events", () ->
                    get(() -> {
                                Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));
                                CompletionStage<HttpResponse> response = PatternsCS.ask(readSide, new ReadSideProtocol.ListEvents(), timeout)
                                        .thenApply(events -> wrap(() -> mapper.writeValueAsString(events)))
                                        .thenApply(events -> HttpResponse.create().withStatus(200).withEntity(events.toString()));

                                return completeWithFuture(response);
                            }

                    )
            );
        }
    }

}
