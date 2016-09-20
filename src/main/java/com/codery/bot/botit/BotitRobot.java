package com.codery.bot.botit;

import com.codery.bot.botit.nativeevents.GlobalEventListener;
import org.jnativehook.GlobalScreen;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiPredicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a bot that executes a {@link Script} object. That's all. :D
 */
public class BotitRobot {

    /**
     * A number representing the absence of a event (mouse/keyboard) code
     */
    public static final int NO_EVENT_CODE = -9999;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BotitRobot.class);
    private final EventLog eventLog;
    private final Robot robot;
    private boolean started;
    private final ExecutorService executor;

    private BiPredicate<Integer, Integer> toggleCondition;

    protected BotitRobot(Robot r, EventLog eventLog, ExecutorService executor) {
        this.robot = r;
        this.eventLog = eventLog;
        this.executor = executor;
    }

    public static BotitRobot newInstance() {
        try {
            return new BotitRobot(new Robot(), new EventLog(), Executors.newSingleThreadExecutor());
        } catch (AWTException e) {
            throw new BotitException("An error occurred trying to instantiate Robot", e);
        }
    }

    public void setToggleCondition(BiPredicate<Integer, Integer> toggleCondition) {
        this.toggleCondition = toggleCondition;
    }

    /**
     * Press the right mouse button
     */
    public void rightClick(int interval) {
        if (started) {
            interval -= 120;
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.delay(60);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
            robot.delay(60);
            if (interval > 0) {
                robot.delay(interval);
            }
        }
    }

    /**
     * Press the left mouse button
     */
    public void leftClick(int interval) {
        if (started) {
            interval -= 200;
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(100);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(100);
            if (interval > 0) {
                robot.delay(interval);
            }
        }
    }

    /**
     * Press a key in an interval.
     *
     * @param code key code to be pressed
     */
    public void pressKey(int code, int interval) {
        if (started) {
            interval -= 200;
            robot.keyPress(code);
            robot.delay(100);
            robot.keyRelease(code);
            robot.delay(100);
            if (interval > 0) {
                robot.delay(interval);
            }
        }
    }

    /**
     * Starts the script's execution.
     *
     * @param scr script to be executed
     */
    public void runScript(Script scr) {
        LOGGER.info("Starting bot execution...");
        setJNativeHookLogLevelToWarning();

        GlobalEventListener.whileListeningForMouseAndKeyEventsDo((mouseListener, keyListener) -> {
            final Integer[] capturedMouseEvtBuffer = {NO_EVENT_CODE};
            final Integer[] capturedKeyEvtBuffer = {NO_EVENT_CODE};
            mouseListener.onMouseReleased(evt -> capturedMouseEvtBuffer[0] = evt.getButton());
            keyListener.onKeyReleased(evt -> capturedKeyEvtBuffer[0] = evt.getRawCode());
            mouseListener.start();
            keyListener.start();

            listenForStartCondition(toggleCondition, capturedMouseEvtBuffer, capturedKeyEvtBuffer);

            while (true) {
                awaitForStartCondition();
                executeScriptActions(scr);
            }
        });

    }

    private void awaitForStartCondition() {
        LOGGER.debug("Awating for start condition to be met...");
        while (!started) {
            sleep(50);
        }
    }

    private void setJNativeHookLogLevelToWarning() {
        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    /**
     * Awaits for the start condition to be met.
     * The bot effectively does not start executing the script until the condition observed by this method is fulfilled.
     * The condition is either an expected mouse key press or keyboard key press.
     *
     * @param startCondidtion the observed condition
     * @param mouseEvtBuffer  mouse events buffer
     * @param keyEvtBuffer    keyboard events buffer
     */
    private void listenForStartCondition(BiPredicate<Integer, Integer> startCondidtion, Integer[] mouseEvtBuffer, Integer[] keyEvtBuffer) {
        executor.execute(() -> {
            while (true) {
                boolean startConditionMet = startCondidtion.test(mouseEvtBuffer[0], keyEvtBuffer[0]);
                if (startConditionMet) {
                    started = !started;
                    mouseEvtBuffer[0] = NO_EVENT_CODE;
                    keyEvtBuffer[0] = NO_EVENT_CODE;
                }
                sleep(50);
            }
        });
    }

    /**
     * Executes each action as soon as each one is finished
     *
     * @param scr execution script
     */
    private void executeScriptActions(Script scr) {
        scr.getActions().forEach(act -> {
            act.execute(this, eventLog);
        });
        //FIXME: leaving the afterscripts execution after all script actions execution can causa bad behavior if the script
        //contains a lot of actions with a lot of delay. e.g.: you'd expect the afteractions to execut right after some
        //action but it will wait for all script (and delays..) to be executed first
        scr.getAfterScripts().forEach((constraint, script) -> {
            if (eventLog.checkConstraint(constraint)) {
                executeScriptActions(script);
            }
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
