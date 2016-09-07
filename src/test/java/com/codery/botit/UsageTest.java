package com.codery.botit;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.Script;
import org.junit.Test;

import java.util.function.BiPredicate;

import static com.codery.bot.botit.Measure.TIMES;
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
        BiPredicate<Integer, Integer> middleButtonPressed = (mouseEvent, keyEvent) -> mouseEvent == MOUSE_BTN_RIGHT;

        BotitRobot bot = BotitRobot.newInstance();
        bot.setToggleCondition(middleButtonPressed);

        bot.runScript(
                new Script()
                        .buff(_5, 5000)
                        .buff(_6, 10000)
                        .cast(_4, 1000)
                        .thenAfter(CAST, _4, 4, TIMES,
                                new Script().cast(_1, 800))
                        .thenAfter(CAST, _4, 8, TIMES,
                                new Script().cast(_2, 800))
        );
    }

}
