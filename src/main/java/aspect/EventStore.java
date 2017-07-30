package aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by david on 2017/07/30.
 */
public class EventStore {
    private Map<String, List<JoinPointEvent>> eventMap = new HashMap<>();
    private static final EventStore eventStore = new EventStore();

    private EventStore() {

    }

    public static EventStore getInstance() {
        return eventStore;
    }

    public void addEvent(JoinPointEvent event) {
        String context = System.getProperty("test.context", "NoContext.method");
        List<JoinPointEvent> events = eventMap.get(context);
        if (events == null) {
            events = new ArrayList<>();
            eventMap.put(context, events);
        }
        events.add(event);
    }

    public List<JoinPointEvent> getEvents(String context) {
        return eventMap.get(context);
    }

    public Set<String> getContexts() {
        return eventMap.keySet();
    }
}
