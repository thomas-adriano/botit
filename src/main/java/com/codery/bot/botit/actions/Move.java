package com.codery.bot.botit.actions;

import com.codery.bot.botit.Coordinate;

/**
 * Created by thomas on 05/08/2016.
 */
public class Move implements Action {

    public static Move mouseTo(Coordinate coord) {
        return new Move();
    }

}
