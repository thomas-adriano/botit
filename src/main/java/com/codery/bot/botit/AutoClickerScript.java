package com.codery.bot.botit;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by thomas on 07/08/2016.f
 */
public class AutoClickerScript implements GlobalKeyListenerObserver {

    private boolean shouldRun = false;

    public static void main(String[] args) throws AWTException, InterruptedException {
        AutoClickerScript script = new AutoClickerScript();
        script.starScript();
    }

    private void starScript() {
        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

        try (GlobalKeyListener keyListener = new GlobalKeyListener(); GlobalMouseListener gml = new GlobalMouseListener()) {
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException ex) {
                throw new BotitException("There was a problem registering the native hook.", ex);
            }

            gml.onMouseReleased((evt) -> {
                if (evt.getButton() == 3 /*middle button*/) {
                    String msg = shouldRun ? "stopping" : "initializing";
                    System.out.println("Key " + evt.getButton() + " pressed, " + msg + " bot...");
                    if (shouldRun) {
                        shouldRun = false;
                    } else {
                        shouldRun = true;
                    }

                }
            });
            gml.start();

            keyListener.registerObserver(this);
            keyListener.start();

            Robot r = new Robot();
            while (true) {
                if (shouldRun) {
                    rightlick(r, 1000);

                }
                if (!shouldRun) {
                    //if it is not running, give it a default loop time (prevent high cpu usage)
                    Thread.sleep(100);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void rightlick(Robot robot, long interval) {
        interval = interval - 200; /*click interval sum*/
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.delay(100);
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            System.err.println("It was not possible to give a " + interval + " second(s) delay");
        }
    }

    @Override
    public void updatePressEvent(NativeKeyEvent evt) {

    }

    @Override
    public void updateReleaseEvent(NativeKeyEvent evt) {

    }

    @Override
    public void updateTypeKeyEvent(NativeKeyEvent evt) {

    }
}
