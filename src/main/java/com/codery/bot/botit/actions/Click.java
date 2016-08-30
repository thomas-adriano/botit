package com.codery.bot.botit.actions;

public abstract class Click extends AbstractAction {

    protected int interval;

    public Click() {
        this(50);
    }

    public Click(int interval) {
        if (interval < 50) {
            interval = 50;
        }
        this.interval = interval;
    }

}
