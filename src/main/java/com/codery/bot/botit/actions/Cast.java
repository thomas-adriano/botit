package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;
import com.codery.bot.botit.EventTypes;

/**
 * Created by Thomas.Adriano on 06/09/2016.
 */
public class Cast extends AbstractAction {

    private final int spell;

    public Cast(int spell, int interval) {
        super(interval);
        this.spell = spell;
    }

    @Override
    public EventTypes getEventType() {
        return EventTypes.CAST;
    }

    @Override
    protected void doExecute(BotitRobot robot) {
        robot.pressKey(spell, interval);
        robot.rightClick(0);
    }
}
