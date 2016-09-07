package com.codery.bot.botit;

import com.codery.bot.botit.actions.ActionFingerprint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Thomas.Adriano on 05/09/2016.
 */
public class EventLog {

    private final Map<ActionFingerprint, Integer> events = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(EventLog.class);

    public synchronized void logEvent(ActionFingerprint fp) {
        Integer quantity = events.get(fp);
        if (quantity == null) {
            quantity = 1;
        } else {
            ++quantity;
        }
        events.put(fp, quantity);
        LOGGER.debug("Logging event " + fp + " with quantity: " + quantity);
    }

    public boolean checkConstraint(Constraint constr) {
        LOGGER.debug("Checking constraint: \"" + constr + "\"");
        for (Map.Entry<ActionFingerprint, Integer> event : events.entrySet()) {
            if (event.getKey().equals(constr.getActionFingerprint()) && (event.getValue() % constr.getQuantity() == 0)) {
                LOGGER.debug("Condition "+constr+" met!");
                return true;
            }
        }

        return false;
    }

    public Integer getEventOccurrence(ActionFingerprint fp) {
        Integer quantity = events.get(fp);
        return quantity == null ? 0 : quantity;
    }

}
