package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventTypes;

public class LeftClick extends Click {
    public LeftClick() {
        super();
    }

    public LeftClick(int interval) {
        super(interval);
    }


    @Override
    public void doExecute(BotitRobot robot) {
        robot.leftClick(interval);
    }

    @Override
    public EventTypes getEventType() {
        return EventTypes.LEFT_CLICK;
    }
}
