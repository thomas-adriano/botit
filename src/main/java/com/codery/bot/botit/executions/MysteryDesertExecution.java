package com.codery.bot.botit.executions;

import com.codery.bot.botit.BotitRobot;
import static com.codery.bot.botit.EventTypes.*;

import static com.codery.bot.botit.Measure.*;
import com.codery.bot.botit.Script;

import java.util.function.BiPredicate;

/**
 * Created by thomasadriano on 9/5/16.
 */
public class MysteryDesertExecution {

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> middleButtonNotPressed = (mouseEvent, keyEvent) -> mouseEvent != MIDDLE_CLICK.getEventCode();
        BiPredicate<Integer, Integer> middleButtonPressed = (mouseEvent, keyEvent) -> mouseEvent == MIDDLE_CLICK.getEventCode();

        BotitRobot bot = BotitRobot.newInstance();
        bot.setStartWhen(middleButtonPressed);
        bot.setWhilee(middleButtonNotPressed);
        bot.setKeepAlive(true);

        bot.runScript(
                new Script()
                        .rightClick(1000)
                        .thenAfter(RIGHT_CLICK, 11, TIMES,
                                new Script().pressKey(_1))
                        .thenAfter(RIGHT_CLICK, 26, TIMES,
                                new Script().pressKey(_3))
        );
    }
}