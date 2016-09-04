package com.codery.bot.botit.executions;

import com.codery.bot.botit.nativeevents.GlobalEventListener;

/**
 * Created by thomas on 30/08/2016.
 */
public class TestEvents {

    public static void main(String[] args) throws InterruptedException {
        GlobalEventListener.whileListeningForMouseAndKeyEventsDo((mouseListener, keyListener) -> {
            mouseListener.onMouseReleased(evt -> System.out.println("-------> "+evt.getButton()));
            keyListener.onKeyReleased(evt -> System.out.println("--------> "+evt.getKeyCode()));

            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
