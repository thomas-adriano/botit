package com.codery.bot.botit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Thomas.Adriano on 05/09/2016.
 */
public class EventLog {

    private final Map<EventTypes, Integer> events = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(EventLog.class);

    public synchronized void logEvent(EventTypes evt) {
        Integer quantity = events.get(evt);
        if (quantity == null) {
            quantity = 1;
        } else {
            ++quantity;
        }
        events.put(evt, quantity);
        LOGGER.debug("Logging event " + evt + " with quantity: " + quantity);
    }

    public boolean checkConstraint(Constraint c) {
        LOGGER.debug("Checking constraint: \"" + c + "\"");
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
