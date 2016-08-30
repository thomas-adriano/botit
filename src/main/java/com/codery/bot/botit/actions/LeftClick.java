package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

import java.util.function.Consumer;

/**
 * Created by Thomas.Adriano on 30/08/2016.
 */
public class LeftClick extends Click {
    public LeftClick() {
        super();
    }

    public LeftClick(long interval) {
        super(interval);
    }

    @Override
    public Consumer<BotitRobot> getExecutionLogic() {
        return robot -> robot.leftClick(super.interval);
    }

}
