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

    public boolean checkConstraint(Constraint c) {
        boolean ret = false;
        for (Map.Entry<EventTypes, Integer> e : events.entrySet()) {
            if (e.getKey() == c.getEvt() && e.getValue() >= c.getQuantity()) {
                ret = true;
                break;
            }
        }

        //if constraint was fulfilled, remove the fulfilled event from the event log
        //like a 'reset' behavior
        if (ret) {
            events.remove(c.getEvt());
        }

        return ret;
    }

    public Integer getEventOccurrence(EventTypes evt) {
        Integer quantity = events.get(evt);
        return quantity == null ? 0 : quantity;
    }

}
