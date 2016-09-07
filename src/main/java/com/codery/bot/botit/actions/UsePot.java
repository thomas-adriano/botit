package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

/**
 * Created by thomasadriano on 9/6/16.
 */
public class UsePot extends AbstractAction {
    private final int pot;

    public UsePot(int pot, int interval) {
        super(interval);
        this.pot = pot;
    }

    @Override
    public ActionFingerprint getFingerprint() {
        return new ActionFingerprint(EventKeys.fromCode(pot), ActionTypes.USE_POT);
    }

    @Override
    protected boolean doExecute(BotitRobot robot) {
        robot.pressKey(pot, interval);
        return true;
    }
}
