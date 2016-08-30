package com.codery.bot.botit.actions;

/**
 * Created by Thomas.Adriano on 30/08/2016.
 */
public abstract class Click implements Action {

    protected long interval;

    public Click() {
        this.interval = 50;
    }

    public Click(long interval) {
        if (interval < 50) {
            interval = 50;
        }
        this.interval = interval;
    }

}
