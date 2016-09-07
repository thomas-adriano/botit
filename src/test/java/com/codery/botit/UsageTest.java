package com.codery.botit;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.Script;
import org.junit.Test;

import java.util.function.BiPredicate;

import static com.codery.bot.botit.MeasureUnit.TIMES;
import static com.codery.bot.botit.actions.ActionTypes.CAST;
import static com.codery.bot.botit.actions.EventKeys.*;


/**
 * Created by thomas on 05/08/2016.
 */
public class UsageTest {

    private static final int MOUSE_BTN_RIGHT = 1;
    private static final int MOUSE_BTN_MIDDLE = 3;
    private static final int MOUSE_BTN_LEFT = 2;

    @Test
    public void usageTest_1() {
        BiPredicate<Integer, Integer> middleButtonPressed = (mouseEvent, keyEvent) -> mouseEvent == MOUSE_BTN_MIDDLE;

        BotitRobot bot = BotitRobot.newInstance();
        bot.setToggleCondition(middleButtonPressed);

        bot.runScript(
                new Script()
                        .buff(F4, 300_000)
                        .buff(F5, 300_000)
                        .buff(F6, 300_000)
                        .buff(F7, 300_000)
                        .cast(_4, 2000)
                        .thenAfter(CAST, _4, 11, TIMES,
                                new Script().usePot(_1, 400))
                        .thenAfter(CAST, _4, 26, TIMES,
                                new Script().usePot(_3, 400))
        );
    }

}
