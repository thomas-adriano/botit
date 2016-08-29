package com.codery.bot.botit.actions;

import com.codery.bot.botit.Coordinate;

import java.util.concurrent.TimeUnit;

/**
 * Created by thomas on 05/08/2016.
 */
public class Press implements Action {

    public static Press key(char key) {
        return new Press();
    }

    public static Press key(String key) {
        return new Press();
    }

    public Press forr(double length, TimeUnit unit) {
        return new Press();
    }

    public Press mouseLeft(Coordinate coor) {
        return new Press();
    }

    public static Press mouseLeft() {
        return new Press();
    }

    public Press mouseRight(Coordinate coord) {
        return new Press();
    }

    public static Press mouseRight() {
        return new Press();
    }

    public Press times(int times) {
        return new Press();
    }
}
