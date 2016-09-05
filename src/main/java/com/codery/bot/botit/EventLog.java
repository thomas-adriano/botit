package com.codery.bot.botit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas.Adriano on 05/09/2016.
 */
public class EventLog {

    private final Map<EventTypes, Integer> events = new HashMap<>();

    public void addEvent(EventTypes evt) {
        Integer quantity = events.get(evt);
        if (quantity == null) {
            events.put(evt, 1);
        } else {
            events.put(evt, quantity++);
        }
    }

    public Integer getEventOccurrence(EventTypes evt) {
        Integer quantity = events.get(evt);
        return quantity == null ? 0 : quantity;
    }

}
