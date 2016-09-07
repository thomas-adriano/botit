package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventLog;

/**
 * Centralizes common {@link Action} logic
 */
public abstract class AbstractAction implements Action {

    protected int interval;

    public AbstractAction(int interval) {
        if (interval < 0) {
            interval = 0;
        }
        this.interval = interval;
    }

    public synchronized void execute(BotitRobot robot, EventLog evtLog) {
        boolean executed = doExecute(robot);
        if (executed) {
            evtLog.logEvent(getFingerprint());
        }
    }

    protected abstract boolean doExecute(BotitRobot robot);

}
