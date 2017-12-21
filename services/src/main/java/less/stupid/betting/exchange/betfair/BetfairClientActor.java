package less.stupid.betting.exchange.betfair;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.betfair.client.BetfairClient;

public class BetfairClientActor extends AbstractActor {

    public static Props props(BetfairClient client) {
        return Props.create(BetfairClientActor.class, () -> new BetfairClientActor(client));
    }

    private final BetfairClient client;

    public BetfairClientActor(BetfairClient client) {
        this.client = client;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(BetfairCommand.ListEvents.class, this::listEvents)
                .build();
    }

    public void listEvents(BetfairCommand.ListEvents cmd) {
        ActorRef sender = getSender();

        client.listEvents(cmd.getFilter()).thenAccept(events -> sender.tell(events, getSelf()));
    }

    public void listMarketCatalogue(BetfairCommand.ListMarketCatalogue cmd) {
        ActorRef sender = getSender();

        client.listMarketCatalogue(cmd.getFilter(), cmd.getMarketProjection(), cmd.getSort(), cmd.getMaxResults()).thenAccept(marketCatalogues -> sender.tell(marketCatalogues, getSelf()));
    }
}
