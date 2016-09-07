package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

/**
 * Created by Thomas.Adriano on 06/09/2016.
 */
public class Cast extends AbstractAction {

    private final int spell;

    public Cast(int spell, int interval) {
        super(interval);
        if (super.interval >= 200) {
            super.interval -= 200; //subtract pressKey inner delay if possible
        }
        this.spell = spell;
    }

    @Override
    public ActionFingerprint getFingerprint() {
        return new ActionFingerprint(EventKeys.fromCode(spell), ActionTypes.CAST);
    }

    @Override
    protected void doExecute(BotitRobot robot) {
        robot.pressKey(spell, interval);
        robot.rightClick(0);
    }
}
