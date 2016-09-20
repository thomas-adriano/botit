package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

/**
 * Created by thomasadriano on 9/6/16.
 */
public class Buff extends AbstractAction {
    private final int buff;
    private long execTime;
    private final int castTime;

    public Buff(int buff, int schedule, int castTime) {
        super(schedule);
        if (super.interval >= 200) {
            super.interval -= 200; //subtract pressKey inner delay if possible
        }
        this.castTime = castTime;
        this.buff = buff;
    }

    @Override
    public ActionFingerprint getFingerprint() {
        return new ActionFingerprint(EventKeys.fromCode(buff), ActionTypes.BUFF);
    }

    @Override
    protected boolean doExecute(BotitRobot robot) {
        if (readyToExecute()) {
            execTime = System.currentTimeMillis();
            robot.pressKey(buff, 1000);
            robot.rightClick(castTime);
            return true;
        }
        return false;
    }

    private boolean readyToExecute() {
        return (System.currentTimeMillis() - execTime) >= interval;
    }


}
