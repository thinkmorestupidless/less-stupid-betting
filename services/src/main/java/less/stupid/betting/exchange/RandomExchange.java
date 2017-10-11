package less.stupid.betting.exchange;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Value;
import scala.concurrent.duration.FiniteDuration;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RandomExchange extends AbstractActor {

    public class Tick {}

    @Value
    public class Bet {

        private final UUID marketId;

        private final UUID selectionId;

        private final Side side;

        private final BigDecimal price;

        private final BigDecimal amount;
    }

    public static Props props(ActorRef commands) {
        return Props.create(RandomExchange.class, () -> new RandomExchange(commands));
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private static final Faker faker = new Faker();

    private final Set<FixtureState> events = Sets.newHashSet();

    private final List<FixtureState> createdEvents = Lists.newArrayList();

    private final ActorRef commands;

    private final ShouldI iShould = new RandomShouldI();

    private Cancellable timer;

    public RandomExchange(ActorRef commands) {
        this.commands = commands;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ExchangeCommand.Start.class, this::start)
                .match(ExchangeCommand.Stop.class, this::stop)
                .match(Tick.class, this::tick)
                .match(FixtureEvent.FixtureCreated.class, this::eventCreated)
                .matchAny(o -> log.warning("i don't know what to do with {}", o)).build();
    }

    public void start(ExchangeCommand.Start cmd) {
        FiniteDuration delay = FiniteDuration.create(1, TimeUnit.SECONDS);
        FiniteDuration interval = FiniteDuration.create(1, TimeUnit.SECONDS);

        timer = getContext().getSystem().scheduler().schedule(delay, interval, getSelf(), new Tick(), getContext().dispatcher(), getSelf());
    }

    public void stop(ExchangeCommand.Stop cmd) {
        if (timer != null && !timer.isCancelled()) {
            timer.cancel();
        }
    }

    private final Random random = new Random();

    public FixtureState randomEvent() {
        int index = random.nextInt(createdEvents.size());
        return createdEvents.get(index);
    }

    public MarketState randomMarket(FixtureState event) {
        Set<MarketState> markets = event.getMarkets();
        int index = random.nextInt(1) * markets.size();
        int i = 0;

        for (MarketState market : markets) {
            if (i == index) {
                return market;
            }
            i++;
        }

        return null;
    }

    public SelectionState randomSelection(MarketState market) {
        Set<SelectionState> selections = market.getSelections();
        int index = random.nextInt(selections.size());
        int i = 0;

        for (SelectionState selection : selections) {
            if (i == index) {
                return selection;
            }
            i++;
        }

        return null;
    }

    public Side randomSide() {
        int index = random.nextInt(Side.values().length);
        return Side.values()[index];
    }

    public void tick(Tick cmd) {

        Set<SelectionState> selectionsChanged = Sets.newHashSet();

        if (iShould.placeBet() && createdEvents.size() > 0) {
            MarketState market = randomMarket(randomEvent());
            SelectionState selection = randomSelection(market);
            Bet bet = new Bet(market.getMarketId(), selection.getSelectionId(), randomSide(), BigDecimal.ONE, BigDecimal.ONE);

            MatchedAndAvailable mAndA = selection.getPrices().forPriceAndSide(bet.getPrice(), bet.getSide());
            mAndA = mAndA.addAvailable(bet.getAmount());

            selection.getPrices().setMatchedAndAvailableAtPriceAndSide(mAndA, bet.getPrice(), bet.getSide());
            selectionsChanged.add(selection);
        }

        if (iShould.createEvent()) {
            Date startTime = faker.date().future(14, TimeUnit.DAYS);
            String exchangeEventId = UUID.randomUUID().toString();

            SelectionState homeTeam = createSelection(faker.team().name());
            SelectionState awayTeam = createSelection(faker.team().name());
            SelectionState draw = createSelection("Draw");

            Set<MarketState> markets = Sets.newHashSet(createMatchOddsMarket(exchangeEventId, Sets.newHashSet(homeTeam, awayTeam, draw)));

            FixtureState event = FixtureState.create()
                    .withStartTime(startTime.getTime())
                    .withExchangeEventId(exchangeEventId)
                    .withExchangeName("random")
                    .withName(homeTeam.getName() + " v " + awayTeam.getName())
                    .withMarkets(markets);

            events.add(event);

            commands.tell(FixtureCommand.create(event.getExchangeName(), event.getExchangeEventId(), event.getName(), event.getStartTime(), event.getMarkets()), getSelf());
        }
    }

    public MarketState createMatchOddsMarket(String exchangeEventId, Set<SelectionState> selections) {
        MarketState market = new MarketState(UUID.randomUUID(), UUID.randomUUID().toString(), exchangeEventId, "MATCH_ODDS", selections);

        return market;
    }

    public SelectionState createSelection(String name) {
        SelectionState selection = new SelectionState(SelectionState.SelectionStatus.RUNNER, UUID.randomUUID(), name, new PriceLadder());

        return selection;
    }

    public void eventCreated(FixtureEvent.FixtureCreated evt) {
        Optional<FixtureState> event = events.stream()
                .filter(e -> e.getExchangeEventId().equals(evt.getExchangeEventId()))
                .findFirst();

        if (event.isPresent()) {
            events.remove(event.get());
            createdEvents.add(event.get());
        }
    }
}
