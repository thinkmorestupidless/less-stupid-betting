package less.stupid.betting.exchange;

import java.util.Random;

public class RandomShouldI implements ShouldI {

    private final Random random = new Random();

    @Override
    public boolean createEvent() {
        return random.nextInt(100) > 90;
    }

    @Override
    public boolean postponeEvent() {
        return random.nextInt(100) > 99;
    }

    @Override
    public boolean cancelEvent() {
        return random.nextInt(100) > 99;
    }

    @Override
    public boolean placeBet() {
        return random.nextInt(100) > 90;
    }
}
