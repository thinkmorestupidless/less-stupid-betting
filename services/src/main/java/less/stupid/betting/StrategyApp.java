package less.stupid.betting;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import less.stupid.betting.exchange.*;
import less.stupid.betting.strategy.StrategyCommand;
import less.stupid.betting.strategy.StrategyEvent;

public class StrategyApp {

    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            startup(new String[] { "3551", "3552", "0" });
        else
            startup(args);
    }

    public static void startup(String[] ports) throws Exception {
        for (String port : ports) {
            // Override the configuration of the port
            Config config = ConfigFactory.parseString(
                    "akka.remote.netty.tcp.port=" + port).withFallback(
                    ConfigFactory.load());

            // Create an Akka system
            ActorSystem system = ActorSystem.create("BettingSystem", config);

            ActorRef events = system.actorOf(Fixtures.props(), "events");
            ActorRef commands = system.actorOf(ExchangeCommandHandler.props(events), "exchange-command-handler");
            ActorRef exchange = system.actorOf(RandomExchange.props(commands), "random-exchange");

            System.out.println("port = " + port);

            if (port.equals("0")) {
                System.out.println("starting");
                exchange.tell(new ExchangeCommand.Start(), system.deadLetters());
            }

//            // Create an actor that starts the sharding and sends random messages
//            ActorRef strategies = system.actorOf(Strategies.props());
//            ActorRef handler = system.actorOf(StrategyCommandHandler.props(strategies));
//
//            if (port.equals("0")) {
//                Thread.sleep(10000);
//                handler.tell(new StrategyCommand.CreateStrategy(), system.deadLetters());
//            }
        }
    }

    public static class StrategyCommandHandler extends AbstractActor {

        public static Props props(ActorRef strategies) {
            return Props.create(StrategyCommandHandler.class, () -> new StrategyCommandHandler(strategies));
        }

        private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

        private final ActorRef strategies;

        public StrategyCommandHandler(ActorRef strategies) {
            this.strategies = strategies;
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(StrategyCommand.class, cmd -> strategies.tell(cmd, getSelf()))
                    .match(StrategyEvent.StrategyCreated.class, evt -> strategies.tell(new StrategyCommand.ActivateStrategy(evt.getStrategyId()), getSelf()))
                    .match(StrategyEvent.StrategyActivated.class, evt -> strategies.tell(new StrategyCommand.ExecuteStrategy(evt.getStrategyId()), getSelf()))
                    .match(StrategyEvent.StrategyExecuted.class, evt -> strategies.tell(new StrategyCommand.DeactivateStrategy(evt.getStrategyId()), getSelf()))
                    .match(StrategyEvent.StrategyDeactivated.class, evt -> strategies.tell(new StrategyCommand.ExecuteStrategy(evt.getStrategyId()), getSelf()))
                    .matchAny(o -> log.info("received -> {}", o))
                    .build();
        }
    }
}
