package com.codery.bot.botit.executions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.Script;
import com.codery.bot.botit.actions.EventKeys;

import java.util.function.BiPredicate;

import static com.codery.bot.botit.Measure.times;
import static com.codery.bot.botit.actions.ActionTypes.CAST;
import static com.codery.bot.botit.actions.EventKeys.*;

/**
 * Created by Jakeline on 9/8/2016.
 */
public class MysteryDesertUpExecution {
    private static final int BUFF_CAST_TIME = 2000;
    private static final int BUFF_SCHEDULE = 305_000;

    public static void main(String[] args) {
        BiPredicate<Integer, Integer> middleButtonPressed = (mouseEvent, keyEvent) -> mouseEvent == EventKeys.MIDDLE_CLICK.getEventCode();

        BotitRobot bot = BotitRobot.newInstance();
        bot.setToggleCondition(middleButtonPressed);

        bot.runScript(
                new Script()
                        //FIXME the first buff is beeing used practically only in the first iteration
                        .buff(F5, BUFF_SCHEDULE, BUFF_CAST_TIME)
                        .buff(F4, BUFF_SCHEDULE, BUFF_CAST_TIME)
                        .buff(F7, BUFF_SCHEDULE, BUFF_CAST_TIME)

                        .cast(F2, 4000)

                        .thenAfter(CAST, F2, times(30),
                                new Script().usePot(_1))
                        .thenAfter(CAST, F2, times(40),
                                new Script().usePot(_3))
        );
    }

}
