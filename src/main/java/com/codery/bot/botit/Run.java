package com.codery.bot.botit;

import com.codery.bot.botit.actions.Action;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by thomas on 05/08/2016.
 */
public class Run {

    public static Run whilee(Predicate<List<String>> cons) {
        return new Run();
    }

    public static Run thiss(Action act) {
        return new Run();
    }

    public Run then(Action act) {
        return new Run();
    }

    public Run and(Action act) {
        return new Run();
    }

}
