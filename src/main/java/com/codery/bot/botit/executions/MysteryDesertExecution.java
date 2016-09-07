package com.codery.bot.botit.executions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.Script;

import java.util.function.BiPredicate;

import static com.codery.bot.botit.actions.EventKeys.*;
import static com.codery.bot.botit.Measure.TIMES;
import static com.codery.bot.botit.actions.ActionTypes.*;

/**
 * Created by thomasadriano on 9/5/16.
 */
public class MysteryDesertExecution {

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> middleButtonPressed = (mouseEvent, keyEvent) -> mouseEvent == MIDDLE_CLICK.getEventCode();

        BotitRobot bot = BotitRobot.newInstance();
        bot.setToggleCondition(middleButtonPressed);

        bot.runScript(
                new Script()
                        .cast(_4, 1000)
                        .thenAfter(CAST, _4, 11, TIMES,
                                new Script().cast(_1))
                        .thenAfter(CAST, _4, 26, TIMES,
                                new Script().cast(_3))
        );
    }
}
