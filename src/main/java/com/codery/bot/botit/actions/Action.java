package com.codery.bot.botit.actions;

import com.codery.bot.botit.BotitRobot;

import java.util.function.Consumer;

/**
 * Created by thomas on 06/08/2016.
 */
public interface Action {

    Consumer<BotitRobot> getExecutionLogic();

}
