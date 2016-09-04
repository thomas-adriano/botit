package com.codery.botit;

import com.codery.bot.botit.BotitRobot;
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

        BotitRobot.newInstance().runScript(
                new Script()
                        .startWhen(middleButtonPressed)
                        .whilee(middleButtonNotPressed)

                        .rightClick(1000)
                        .after(11, Measures.TIMES, scr -> scr.pressKey(1 /*stm pot*/))
                        .after(26, Measures.TIMES, scr -> scr.pressKey(3 /*mana pot*/))

                        .keepAlive()
        );
    }

}
