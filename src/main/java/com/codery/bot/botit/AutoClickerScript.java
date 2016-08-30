package com.codery.bot.botit;

import org.jnativehook.GlobalScreen;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by thomas on 07/08/2016.f
 */
public class AutoClickerScript {

    private boolean running = false;

    public static void main(String[] args) throws AWTException, InterruptedException {
        AutoClickerScript script = new AutoClickerScript();
        script.starScript();
    }

    private void setJNativeHookLogLevelToWarning() {
        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    private void starScript() {
        BotitRobot robot = BotitRobot.newInstance();
        setJNativeHookLogLevelToWarning();
        GlobalEventListener.whileListeningForMouseAndKeyEventsDo((mouseListener, keyListener) -> {
            mouseListener.onMouseReleased((evt) -> {
                if (evt.getButton() == 3 /*middle button*/) {
                    String msg = running ? "stopping" : "initializing";
                    System.out.println("Key " + evt.getButton() + " pressed, " + msg + " bot...");
                    if (running) {
                        running = false;
                    } else {
                        running = true;
                    }
                }
            });
            mouseListener.start();

            while (true) {
                if (running) {
                    robot.rightClick(1000);
                }
                if (!running) {
                    //if it is not running, give it a default loop time (prevent high cpu usage)
                    robot.sleep(100);
                }
            }
        });
    }

    private void robotTest() {
        BotitRobot.newInstance().runScript(
                Script.newInstance().rightClick(1000).whilee((mouseEvent, keyEvent) -> mouseEvent == null || mouseEvent.getButton() != 3)
        );
    }

}
