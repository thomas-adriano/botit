package com.codery.bot.botit;

import com.codery.bot.botit.actions.Action;
import com.codery.bot.botit.actions.KeyPress;
import com.codery.bot.botit.actions.LeftClick;
import com.codery.bot.botit.actions.RightClick;

import java.util.*;

/**
 * Represents a script, in the sense of a list of steps to be executed.
 */
public class Script {

    private final List<Action> actions;
    private final Map<Constraint, Script> afterScripts;

    public Script() {
        this.actions = null;
        this.afterScripts = null;
    }

    private Script(List<Action> actions, Map<Constraint, Script> afterScripts) {
        this.actions = actions;
        this.afterScripts = afterScripts;
    }

    public Script pressKey(EventTypes evt) {
        return this.pressKey(evt, 0);
    }

    public Script pressKey(EventTypes evt, int interval) {
        return newAction(new KeyPress(evt.getEventCode(), interval));
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
        return new Script(newActions, this.afterScripts);
    }

    public Script thenAfter(EventTypes evt, int times, Measure measure, Script scr) {
        Map<Constraint, Script> newAfterScripts = new HashMap<>();
        if (afterScripts != null) {
            newAfterScripts.putAll(afterScripts);
        }
        Constraint c = new Constraint(evt, times, measure);
        newAfterScripts.put(c, scr);
        return new Script(this.actions, newAfterScripts);
    }

    public List<Action> getActions() {
        return actions == null ? Collections.emptyList() : actions;
    }

    public Map<Constraint, Script> getAfterScripts() {
        return afterScripts == null ? Collections.emptyMap() : afterScripts;
    }
}
