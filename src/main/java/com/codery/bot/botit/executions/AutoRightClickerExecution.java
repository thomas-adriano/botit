package com.codery.bot.botit.executions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.Script;

import java.util.function.BiPredicate;

import static com.codery.bot.botit.actions.EventKeys._4;

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
        BiPredicate<Integer, Integer> middleButtonPressed = (mouseEvent, keyEvent) -> mouseEvent == MOUSE_BTN_MIDDLE;

        BotitRobot bot = BotitRobot.newInstance();
        bot.setToggleCondition(middleButtonPressed);

        bot.runScript(
                new Script()
                        .cast(_4, 1000)
        );
    }
}
