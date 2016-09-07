package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

/**
 * Representation of an {@link com.codery.bot.botit.Script} action.
 */
public interface Action {

    ActionFingerprint getFingerprint();

    /**
     * Iniitates this action execution logic.
     *
     * @param robot {@link BotitRobot} instance in wich the effective action will be called
     */
    void execute(BotitRobot robot);

}
