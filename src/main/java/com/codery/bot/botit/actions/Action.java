package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventLog;
import com.codery.bot.botit.EventTypes;

/**
 * Representation of an {@link com.codery.bot.botit.Script} action.
 */
public interface Action {

    EventTypes getEventType();

    /**
     * Iniitates this action execution logic.
     *
     * @param robot {@link BotitRobot} instance in wich the effective action will be called
     */
    void execute(BotitRobot robot, EventLog evtLog);

    /**
     * Signals this action's termination status.
     *
     * @return true if terminated, false otherwise
     */
    boolean terminated();

}
