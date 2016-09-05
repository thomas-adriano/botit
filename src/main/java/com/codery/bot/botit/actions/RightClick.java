package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventTypes;

public class RightClick extends Click {
    public RightClick() {
        super();
    }

    public RightClick(int interval) {
        super(interval);
    }

    @Override
    public void doExecute(BotitRobot robot) {
        LOGGER.debug("executing action " + toString());
        robot.rightClick(interval);
    }

    @Override
    public EventTypes getEventType() {
        return EventTypes.RIGHT_CLICK;
    }

    @Override
    public String toString() {
        return "RightClick{}";
    }
}
