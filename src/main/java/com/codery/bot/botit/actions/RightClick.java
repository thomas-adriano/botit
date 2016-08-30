package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.actions.Action;

import java.util.function.Consumer;

/**
 * Created by thomasadriano on 8/29/16.
 */
public class RightClick implements Action {

    public long interval;

    public RightClick() {
        this.interval = 50;
    }

    public RightClick(long interval) {
        this.interval = interval;
    }

    @Override
    public Consumer<BotitRobot> getExecutionLogic() {
        if (interval < 50) {
            interval = 50;
        }
        return (robot) -> {
            robot.rightClick(interval);
        };
    }

    public RightClick interval(long interval) {
        this.interval = interval;
        return new RightClick(interval);
    }
}
