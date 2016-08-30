package com.codery.bot.botit.executions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.Script;

import java.util.function.BiPredicate;

/**
 * Simple auto clicker that:
 * <pre>
 *     - starts only after the middle mouse button is clicked;
 *     - clicks only with the right button in an interval close to 1 second;
 *     - pauses execution if the middle mouse button is clicked;
 * </pre>
 */
public class AutoRightClickerExecution {

    private static final int MOUSE_BTN_MIDDLE = 3;

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> middleButtonNotPressed = (mouseEvent, keyEvent) -> mouseEvent != MOUSE_BTN_MIDDLE;
        BiPredicate<Integer, Integer> middleButtonPressed = (mouseEvent, keyEvent) -> mouseEvent == MOUSE_BTN_MIDDLE;

        BotitRobot.newInstance().runScript(
                new Script()
                        .startWhen(middleButtonPressed)
                        .whilee(middleButtonNotPressed)
                        .rightClick(1000)
                        .keepAlive()
        );
    }
}
