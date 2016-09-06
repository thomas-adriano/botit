package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventLog;

/**
 * Centralizes common {@link Action} logic
 */
public abstract class AbstractAction implements Action {

    protected int interval;
    private long execTime;
    private boolean executing;

    public synchronized void execute(BotitRobot robot, EventLog evtLog) {
        executing = true;
        doExecute(robot);
        execTime = System.currentTimeMillis();
        evtLog.logEvent(this.getEventType());
        executing = false;
    }

    protected abstract void doExecute(BotitRobot robot);

    @Override
    public boolean readyToExecute() {
        long elapsed = System.currentTimeMillis() - execTime;
        return elapsed >= interval && !executing;
    }
}
