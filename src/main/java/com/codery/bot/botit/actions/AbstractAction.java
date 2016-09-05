package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventLog;

/**
 * Centralizes common {@link Action} logic
 */
public abstract class AbstractAction implements Action {

    public synchronized void execute(BotitRobot robot, EventLog evtLog) {
        doExecute(robot);
        evtLog.logEvent(this.getEventType());
    }

    protected abstract void doExecute(BotitRobot robot);

}
