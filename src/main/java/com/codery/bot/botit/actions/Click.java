package com.codery.bot.botit.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Click extends AbstractAction {

    protected final static Logger LOGGER = LoggerFactory.getLogger(KeyPress.class);
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
