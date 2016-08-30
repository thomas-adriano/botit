package com.codery.bot.botit;

import com.codery.bot.botit.actions.Action;
import com.codery.bot.botit.actions.KeyPress;
import com.codery.bot.botit.actions.LeftClick;
import com.codery.bot.botit.actions.RightClick;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Represents a script, in the sense of a list of steps to be executed.
 */
public class Script {

    private final boolean forever;
    private final int times;
    private final BiPredicate<Integer, Integer> whilee;
    private final List<Action> actions;
    private final boolean keepAlive;
    private final BiPredicate<Integer, Integer> startWhen;

    public Script() {
        this.forever = false;
        this.times = 0;
        this.whilee = null;
        this.actions = null;
        this.keepAlive = false;
        this.startWhen = null;
    }

    private Script(boolean forever, int times, BiPredicate<Integer, Integer> whilee, List<Action> actions,
                   boolean keepAlive, BiPredicate<Integer, Integer> startWhen) {
        this.forever = forever;
        this.times = times;
        this.whilee = whilee;
        this.actions = actions;
        this.keepAlive = keepAlive;
        this.startWhen = startWhen;
    }

    public Script pressKey(int code, int interval) {
        List<Action> newActions = new ArrayList<>();
        if (actions != null) {
            newActions.addAll(actions);
        }
        newActions.add(new KeyPress(code, interval));
        return new Script(this.forever, this.times, this.whilee, newActions, this.keepAlive, this.startWhen);
    }

    public Script rightClick(int interval) {
        List<Action> newActions = new ArrayList<>();
        if (actions != null) {
            newActions.addAll(actions);
        }
        newActions.add(new RightClick(interval));
        return new Script(this.forever, this.times, this.whilee, newActions, this.keepAlive, this.startWhen);
    }

    public Script leftClick(int interval) {
        List<Action> newActions = new ArrayList<>();
        if (actions != null) {
            newActions.addAll(actions);
        }
        newActions.add(new LeftClick(interval));
        return new Script(this.forever, this.times, this.whilee, newActions, this.keepAlive, this.startWhen);
    }

    public Script forever() {
        return new Script(true, this.times, this.whilee, this.actions, this.keepAlive, this.startWhen);
    }

    public Script times(int times) {
        return new Script(this.forever, times, this.whilee, this.actions, this.keepAlive, this.startWhen);
    }

    public Script whilee(BiPredicate<Integer, Integer> p) {
        return new Script(this.forever, this.times, p, this.actions, this.keepAlive, this.startWhen);
    }

    public Script keepAlive() {
        return new Script(this.forever, this.times, this.whilee, this.actions, true, this.startWhen);
    }

    public Script startWhen(BiPredicate<Integer, Integer> startWhen) {
        return new Script(this.forever, this.times, this.whilee, this.actions, this.keepAlive, startWhen);
    }

    public boolean isForever() {
        return forever;
    }

    public int getTimes() {
        return times;
    }

    public BiPredicate<Integer, Integer> getWhilee() {
        return whilee;
    }

    public List<Action> getActions() {
        return actions;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public BiPredicate<Integer, Integer> getStartWhen() {
        return startWhen;
    }
}
