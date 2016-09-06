package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyPress extends AbstractAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(KeyPress.class);
    private final int code;

    public KeyPress(int code) {
        this(code, 50);
    }

    public KeyPress(int code, int interval) {
        this.code = code;
        this.interval = interval;
    }

    @Override
    public void doExecute(BotitRobot robot) {
        LOGGER.debug("executing action " + toString());
        robot.pressKey(code);
    }

    @Override
    public String toString() {
        return "KeyPress{" +
                "code=" + code +
                ", interval=" + interval +
                '}';
    }

    @Override
    public EventTypes getEventType() {
        return EventTypes.fromCode(code);
    }
}
