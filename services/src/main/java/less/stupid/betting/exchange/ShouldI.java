package less.stupid.betting.exchange;

public interface ShouldI {

    boolean createEvent();

    boolean postponeEvent();

    boolean cancelEvent();

    boolean placeBet();
}
