package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

/**
 * Created by thomasadriano on 9/6/16.
 */
public class Buff extends AbstractAction {
    private final int buff;
    private long execTime;

    public Buff(int buff, int interval) {
        super(interval);
        if (super.interval >= 200) {
            super.interval -= 200; //subtract pressKey inner delay if possible
        }
        this.buff = buff;
    }

    @Override
    public ActionFingerprint getFingerprint() {
        return new ActionFingerprint(EventKeys.fromCode(buff), ActionTypes.BUFF);
    }

    @Override
    protected void doExecute(BotitRobot robot) {
        if (readyToExecute()) {
            execTime = System.currentTimeMillis();
            robot.pressKey(buff, 800);
            robot.rightClick(0);
        }
    }

    private boolean readyToExecute() {
        return (System.currentTimeMillis() - execTime) >= interval;
    }


}
