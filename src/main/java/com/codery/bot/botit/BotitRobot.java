package com.codery.bot.botit;

import com.codery.bot.botit.nativeevents.GlobalEventListener;
import org.jnativehook.GlobalScreen;

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
    private final EventLog eventLog;
    private final Robot robot;
    private final ExecutorService executor;
    private boolean terminated = false;

    private boolean keepAlive;
    private boolean forever;
    private int times;
    private BiPredicate<Integer, Integer> startWhen;
    private BiPredicate<Integer, Integer> whilee;

    protected BotitRobot(Robot r, ExecutorService executor, EventLog eventLog) {
        this.robot = r;
        this.executor = executor;
        this.eventLog = eventLog;
    }

    public static BotitRobot newInstance() {
        try {
            return new BotitRobot(new Robot(), Executors.newCachedThreadPool(), new EventLog());
        } catch (AWTException e) {
            throw new BotitException("An error occurred trying to instantiate Robot", e);
        }
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public void setForever(boolean forever) {
        this.forever = forever;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void setStartWhen(BiPredicate<Integer, Integer> startWhen) {
        this.startWhen = startWhen;
    }

    public void setWhilee(BiPredicate<Integer, Integer> whilee) {
        this.whilee = whilee;
    }

    /**
     * Press the right mouse button
     *
     * @param interval interval to be given at each pressing
     */
    public void rightClick(int interval) {
        if (!terminated) {
            interval = interval - 200; /*click interval sum*/
            robot.mousePress(InputEvent.BUTTON3_MASK);
            robot.delay(100);
            robot.mouseRelease(InputEvent.BUTTON3_MASK);
            robot.delay(100);
            robot.delay(interval > 0 ? interval : 0);
        }
    }

    /**
     * Press the left mouse button
     *
     * @param interval interval to be given at each pressing
     */
    public void leftClick(int interval) {
        if (!terminated) {
            interval = interval - 200; /*click interval sum*/
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.delay(100);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.delay(100);
            robot.delay(interval > 0 ? interval : 0);
        }
    }

    /**
     * Press a key in an interval.
     *
     * @param code     key code to be pressed
     * @param interval interval to be given at each pressing
     */
    public void pressKey(int code, int interval) {
        if (!terminated) {
            interval = interval - 200; /*press interval sum*/
            robot.keyPress(code);
            robot.delay(100);
            robot.keyRelease(code);
            robot.delay(100);
            robot.delay(interval > 0 ? interval : 0);
        }
    }

    /**
     * Starts the script's execution.
     *
     * @param scr script to be executed
     */
    public void runScript(Script scr) {
        setJNativeHookLogLevelToWarning();
        if (forever) {
            while (true) {
                executeScriptActions(scr);
            }
        } else {
            boolean hasTimes = times > 0;
            boolean hasWhile = whilee != null;
            boolean hasConditionalStart = startWhen != null;

            GlobalEventListener.whileListeningForMouseAndKeyEventsDo((mouseListener, keyListener) -> {
                final Integer[] capturedMouseEvtBuffer = {NO_EVENT_CODE};
                final Integer[] capturedKeyEvtBuffer = {NO_EVENT_CODE};
                mouseListener.onMouseReleased(evt -> capturedMouseEvtBuffer[0] = evt.getButton());
                keyListener.onKeyReleased(evt -> capturedKeyEvtBuffer[0] = evt.getKeyCode());
                mouseListener.start();
                keyListener.start();

                while (true) {
                    if (hasConditionalStart) {
                        awaitStartCondition(startWhen, capturedMouseEvtBuffer, capturedKeyEvtBuffer);
                    }

                    terminated = false;
                    if (hasTimes && hasWhile) {
                        for (int i = 0; i < times || whilee.test(capturedMouseEvtBuffer[0], capturedKeyEvtBuffer[0]); i++) {
                            executeScriptActions(scr);
                        }
                    } else if (hasTimes) {
                        for (int i = 0; i < times; i++) {
                            executeScriptActions(scr);
                        }
                    } else if (hasWhile) {
                        while (whilee.test(capturedMouseEvtBuffer[0], capturedKeyEvtBuffer[0])) {
                            executeScriptActions(scr);
                        }
                    }

                    capturedMouseEvtBuffer[0] = NO_EVENT_CODE; //reset last mouse event
                    terminated = true;
                    if (keepAlive) {
                        sleep(50);
                    } else {
                        break;
                    }
                }
            });
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
    private void awaitStartCondition(BiPredicate<Integer, Integer> startCondidtion, Integer[] mouseEvtBuffer, Integer[] keyEvtBuffer) {
        boolean startConditionMet = startCondidtion.test(mouseEvtBuffer[0], keyEvtBuffer[0]);
        while (!startConditionMet) {
            sleep(50);
            startConditionMet = startCondidtion.test(mouseEvtBuffer[0], keyEvtBuffer[0]);
        }
        mouseEvtBuffer[0] = NO_EVENT_CODE; //reset last mouse event
    }

    /**
     * Executes each action as soon as each one is finished
     *
     * @param scr execution script
     */
    private void executeScriptActions(Script scr) {
        scr.getActions().forEach(act -> {
            if (act.terminated()) {
                executor.execute(() -> act.execute(this, eventLog));
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
