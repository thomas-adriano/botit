package com.codery.botit;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventTypes;
import com.codery.bot.botit.Measure;
import com.codery.bot.botit.Script;
import org.junit.Test;

import java.util.function.BiPredicate;

/**
 * Created by thomas on 05/08/2016.
 */
public class UsageTest {

    private static final int MOUSE_BTN_MIDDLE = 3;

    @Test
    public void usageTest_1() {
        BiPredicate<Integer, Integer> middleButtonNotPressed = (mouseEvent, keyEvent) -> mouseEvent != MOUSE_BTN_MIDDLE;
        BiPredicate<Integer, Integer> middleButtonPressed = (mouseEvent, keyEvent) -> mouseEvent == MOUSE_BTN_MIDDLE;

        BotitRobot bot = BotitRobot.newInstance();
        bot.setStartWhen(middleButtonPressed);
        bot.setWhilee(middleButtonNotPressed);
        bot.setKeepAlive(true);

        bot.runScript(
                new Script()
                        .rightClick(1000)
                        .thenAfter(EventTypes.RIGHT_CLICK, 3, Measure.TIMES,
                                new Script().pressKey(EventTypes._4, 1000))
        );
    }

}
