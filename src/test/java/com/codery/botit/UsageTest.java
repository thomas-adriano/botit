package com.codery.botit;

import com.codery.bot.botit.*;
import com.codery.bot.botit.actions.Move;
import com.codery.bot.botit.actions.Press;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by thomas on 05/08/2016.
 */
public class UsageTest {

    @Test
    public void usageTest_1() {
        Bot.it(
                Run.whilee((keysPressed) -> !keysPressed.contains("ESC"))
                        .thiss(Press.key('A').forr(1, TimeUnit.SECONDS))
                        .then(Press.mouseLeft().times(2))
                        .then(Move.mouseTo(new Coordinate(44, 5)))
                        .then(Press.mouseRight().times(1))
                        .then(Press.key('A').forr(1, TimeUnit.SECONDS)).and(Press.key("ALT").forr(1, TimeUnit.SECONDS))
        );
    }

}
