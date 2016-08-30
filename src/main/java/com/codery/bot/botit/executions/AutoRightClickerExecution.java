package com.codery.bot.botit.executions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.Script;

import java.util.function.BiPredicate;

public class AutoRightClickerExecution {

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> middleButtonNotPressed = (mouseEvent, keyEvent) -> mouseEvent != 3;
        BiPredicate<Integer, Integer> middleButtonPressed = (mouseEvent, keyEvent) -> mouseEvent == 3;

        BotitRobot.newInstance().runScript(
                new Script()
                        .startWhen(middleButtonPressed)
                        .whilee(middleButtonNotPressed)
                        .rightClick(1000)
                        .keepAlive()
        );
    }
}
