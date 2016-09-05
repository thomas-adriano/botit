package com.codery.bot.botit;

import com.codery.bot.botit.actions.Action;
import com.codery.bot.botit.actions.KeyPress;
import com.codery.bot.botit.actions.LeftClick;
import com.codery.bot.botit.actions.RightClick;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Represents a script, in the sense of a list of steps to be executed.
 */
public class Script {

    private final List<Action> actions;

    public Script() {
        this.actions = null;
    }

    private Script(List<Action> actions) {
        this.actions = actions;
    }

    public Script pressKey(EventTypes evt) {
        return this.pressKey(evt, 0);
    }

    public Script pressKey(EventTypes evt, int interval) {
        return newAction(new KeyPress(Integer.valueOf(evt.value()), interval));
    }

    public Script rightClick() {
        return this.rightClick(0);
    }

    public Script rightClick(int interval) {
        return newAction(new RightClick(interval));
    }

    public Script leftClick() {
        return this.leftClick(0);
    }

    public Script leftClick(int interval) {
        return newAction(new LeftClick(interval));
    }

    private Script newAction(Action act) {
        List<Action> newActions = new ArrayList<>();
        if (actions != null) {
            newActions.addAll(actions);
        }
        newActions.add(act);
        return new Script(newActions);
    }

    public List<Action> getActions() {
        return actions;
    }

}
