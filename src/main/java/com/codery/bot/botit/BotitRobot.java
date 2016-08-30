package com.codery.bot.botit;

import org.jnativehook.mouse.NativeMouseEvent;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by thomasadriano on 8/29/16.
 */
public class BotitRobot {

    private final Robot robot;

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

    //TODO NA VERDADE O TIMES E O WHILEE DEVERIAM RODAR JUNTOS, PARANDO QUANDO O PRIMERIO DOS DOIS FOR ATINGIDO
    public void runScript(Script scr) {
        if (scr.isForever()) {
            while (true) {
                executeScriptActions(scr);
            }
        } else if (scr.getTimes() > 0) {
            for (int i = 0; i < scr.getTimes(); i++) {
                executeScriptActions(scr);
            }
        } else if (scr.getWhilee() != null) {
            GlobalEventListener.whileListeningForMouseAndKeyEventsDo((mouseListener, keyListener) -> {
                final NativeMouseEvent[] capturedEvt = {null};
                mouseListener.onMouseReleased((evt) -> {
                    capturedEvt[0] = evt;
                });
                mouseListener.start();
                while (scr.getWhilee().test(capturedEvt[0], null)) {
                    executeScriptActions(scr);
                }
            });
        }
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
