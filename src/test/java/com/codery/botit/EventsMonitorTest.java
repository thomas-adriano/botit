package com.codery.botit;

import com.codery.bot.botit.nativeevents.GlobalEventListener;
import org.jnativehook.GlobalScreen;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Thomas.Adriano on 05/09/2016.
 */
public class EventsMonitorTest {

    @Test
    public void test() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);

        GlobalEventListener.whileListeningForMouseAndKeyEventsDo((mouseListener, keyListener) -> {
            keyListener.start();
            mouseListener.start();
            keyListener.onKeyPressed(keyEvent -> System.out.println(keyEvent.getRawCode()));
            mouseListener.onMousePressed(mouseEvent -> System.out.println(mouseEvent.getButton()));
            while (true) {


                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
