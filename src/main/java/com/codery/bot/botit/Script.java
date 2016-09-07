package com.codery.bot.botit;

import com.codery.bot.botit.actions.*;

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

    public Script buff(EventKeys evt) {
        return buff(evt, 0);
    }

    public Script buff(EventKeys evt, int interval) {
        return newAction(new Buff(evt.getEventCode(), interval));
    }

    public Script cast(EventKeys evt) {
        return cast(evt, 0);
    }

    public Script cast(EventKeys evt, int interval) {
        return newAction(new Cast(evt.getEventCode(), interval));
    }

    private Script newAction(Action act) {
        List<Action> newActions = new ArrayList<>();
        if (actions != null) {
            newActions.addAll(actions);
        }
        newActions.add(act);
        return new Script(newActions, this.afterScripts);
    }

    public Script thenAfter(ActionTypes actType, EventKeys evt, int times, Measure measure, Script scr) {
        Map<Constraint, Script> newAfterScripts = new HashMap<>();
        if (afterScripts != null) {
            newAfterScripts.putAll(afterScripts);
        }
        Constraint c = new Constraint(new ActionFingerprint(evt, actType), times, measure);
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
