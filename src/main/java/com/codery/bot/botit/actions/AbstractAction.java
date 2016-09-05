package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventLog;

/**
 * Centralizes common {@link Action} logic
 */
public abstract class AbstractAction implements Action {

    private boolean terminated = true;

    public synchronized void execute(BotitRobot robot, EventLog evtLog) {
        terminated = false;
        doExecute(robot);
        terminated = true;
        evtLog.logEvent(this.getEventType());
    }

    protected abstract void doExecute(BotitRobot robot);

    public boolean terminated() {
        return terminated;
    }

}
