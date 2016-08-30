package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

import java.util.function.Consumer;

/**
 * Created by Thomas.Adriano on 30/08/2016.
 */
public class LeftClick implements Action {
    public long interval;

    public LeftClick() {
        this.interval = 50;
    }

    public LeftClick(long interval) {
        this.interval = interval;
    }

    @Override
    public Consumer<BotitRobot> getExecutionLogic() {
        if (interval < 50) {
            interval = 50;
        }
        return (robot) -> {
            robot.leftClick(interval);
        };
    }

}
