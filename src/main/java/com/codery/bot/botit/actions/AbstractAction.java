package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

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

    public synchronized void execute(BotitRobot robot) {
        doExecute(robot);
    }

    protected abstract void doExecute(BotitRobot robot);

}
