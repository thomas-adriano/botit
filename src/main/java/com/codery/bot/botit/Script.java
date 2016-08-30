package com.codery.bot.botit;

import com.codery.bot.botit.actions.Action;
import com.codery.bot.botit.actions.RightClick;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Created by thomas on 05/08/2016.
 */
public class Script {

    private final boolean forever;
    private final int times;
    private final BiPredicate<NativeMouseEvent, NativeKeyEvent> whilee;
    private final List<Action> actions;

    public Script(boolean forever, int times, BiPredicate<NativeMouseEvent, NativeKeyEvent> whilee, List<Action> actions) {
        this.forever = forever;
        this.times = times;
        this.whilee = whilee;
        this.actions = actions;
    }

    public Script(Script scr) {
        this.forever = scr.forever;
        this.times = scr.times;
        this.whilee = scr.whilee;
        this.actions = scr.actions;
    }

    public static Script newInstance() {
        return new Script(false, 0, null, null);
    }

    public Script rightClick(long interval) {
        List<Action> newActions = new ArrayList<>(actions);
        newActions.add(new RightClick().interval(interval));
        return new Script(this.forever, this.times, this.whilee, newActions);
    }

    public Script leftClick() {
        return this;
    }

    public Script forever() {
        return this;
    }

    public Script times(int times) {
        return this;
    }

    public Script whilee(BiPredicate<NativeMouseEvent, NativeKeyEvent> p) {
        return new Script(this.forever, this.times, p, this.actions);
    }

    public boolean isForever() {
        return forever;
    }

    public int getTimes() {
        return times;
    }

    public BiPredicate<NativeMouseEvent, NativeKeyEvent> getWhilee() {
        return whilee;
    }

    public List<Action> getActions() {
        return actions;
    }
}
