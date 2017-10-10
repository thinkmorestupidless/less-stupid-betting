package less.stupid.betting.exchange;

import com.datastax.driver.core.Row;
import lombok.Value;

import java.util.Comparator;
import java.util.UUID;

@Value
public class XEvent {

    public static Comparator<XEvent> ascendingStartTime = (a, b) -> Long.compare(a.getStartTime(), b.getStartTime());

    private final UUID eventId;

    private final String exchangeName;

    private final String exchangeEventId;

    private final String eventName;

    private final long startTime;

    public static XEvent create(Row row) {
        return new XEvent(row.getUUID("eventId"), row.getString("exchangeEventId"), row.getString("exchangeName"), row.getString("name"), row.getLong("startTime"));
    }
}
