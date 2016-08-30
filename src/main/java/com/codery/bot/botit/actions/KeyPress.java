package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

public class KeyPress extends AbstractAction {

    private final int code;
    private final int interval;

    public KeyPress(int code) {
        this(code, 50);
    }

    public KeyPress(int code, int interval) {
        this.code = code;
        this.interval = interval;
    }

    @Override
    public void doExecute(BotitRobot robot) {
        robot.pressKey(code, interval);
    }

}
