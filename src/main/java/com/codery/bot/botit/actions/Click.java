package com.codery.bot.botit.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Click extends AbstractAction {

    protected final static Logger LOGGER = LoggerFactory.getLogger(KeyPress.class);

    public Click() {
        this(50);
    }

    public Click(int interval) {
        super(interval);
    }
}
