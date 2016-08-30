package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

/**
 * Centralizes common {@link Action} logic
 */
public abstract class AbstractAction implements Action {

    private boolean terminated = true;

    public synchronized void execute(BotitRobot robot) {
        terminated = false;
        doExecute(robot);
        terminated = true;
    }

    protected abstract void doExecute(BotitRobot robot);

    public boolean terminated() {
        return terminated;
    }

}
