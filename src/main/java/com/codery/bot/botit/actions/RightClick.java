package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

import java.util.function.Consumer;

/**
 * Created by thomasadriano on 8/29/16.
 */
public class RightClick extends Click {
    public RightClick() {
        super();
    }

    public RightClick(long interval) {
        super(interval);
    }

    @Override
    public Consumer<BotitRobot> getExecutionLogic() {
        return robot -> robot.rightClick(super.interval);
    }
}
