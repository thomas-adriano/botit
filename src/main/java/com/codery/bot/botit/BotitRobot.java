package com.codery.bot.botit;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by thomasadriano on 8/29/16.
 */
public class BotitRobot {

    private final Robot robot;
    public static final int NO_KEY_CODE = -9999;

    public BotitRobot(Robot r) {
        this.robot = r;
    }

    public static BotitRobot newInstance() {
        try {
            return new BotitRobot(new Robot());
        } catch (AWTException e) {
            throw new BotitException("An error occurred trying to instantiate Robot", e);
        }
    }

    public void rightClick(long interval) {
        interval = interval - 200; /*click interval sum*/
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.delay(100);
        sleep(interval);
    }

    public void leftClick(long interval) {
        interval = interval - 200; /*click interval sum*/
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        sleep(interval);
    }

    private void setJNativeHookLogLevelToWarning() {
        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    public void runScript(Script scr) {
        setJNativeHookLogLevelToWarning();
        if (scr.isForever()) {
            while (true) {
                executeScriptActions(scr);
            }
        } else {
            boolean hasTimes = scr.getTimes() > 0;
            boolean hasWhile = scr.getWhilee() != null;
            while (true) {
                GlobalEventListener.whileListeningForMouseAndKeyEventsDo((mouseListener, keyListener) -> {
                    mouseListener.start();
                    final Integer[] capturedMouseEvtBuffer = {NO_KEY_CODE};
                    mouseListener.onMouseReleased((evt) -> capturedMouseEvtBuffer[0] = evt.getButton());
                    final Integer[] capturedKeyEvtBuffer = {NO_KEY_CODE};
                    keyListener.onKeyReleased(evt -> capturedKeyEvtBuffer[0] = evt.getKeyCode());

                    if (scr.getStartWhen() != null) {
                        awaitStartCondition(scr.getStartWhen(), capturedMouseEvtBuffer, capturedKeyEvtBuffer);
                    }

                    if (hasTimes && hasWhile) {
                        for (int i = 0; i < scr.getTimes() || scr.getWhilee().test(capturedMouseEvtBuffer[0], null); i++) {
                            executeScriptActions(scr);
                        }
                    } else if (hasTimes) {
                        for (int i = 0; i < scr.getTimes(); i++) {
                            executeScriptActions(scr);
                        }
                    } else if (hasWhile) {
                        while (scr.getWhilee().test(capturedMouseEvtBuffer[0], null)) {
                            executeScriptActions(scr);
                        }
                    }
                });

                if (scr.isKeepAlive()) {
                    sleep(50);
                } else {
                    break;
                }
            }
        }
    }

    private void awaitStartCondition(BiPredicate<Integer, Integer> startCondidtion, Integer[] mouseEvt, Integer[] keyEvt) {
        boolean startConditionMet = startCondidtion.test(mouseEvt[0], keyEvt[0]);
        while (!startConditionMet) {
            sleep(50);
            startConditionMet = startCondidtion.test(mouseEvt[0], keyEvt[0]);
        }
        mouseEvt[0] = NO_KEY_CODE; //reset last mouse event
    }

    private void executeScriptActions(Script scr) {
        scr.getActions().forEach((act) -> {
            act.getExecutionLogic().accept(this);
        });
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
