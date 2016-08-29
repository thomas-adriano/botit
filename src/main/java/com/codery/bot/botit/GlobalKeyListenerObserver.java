package com.codery.bot.botit;

import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * Created by thomas on 07/08/2016.
 */
public interface GlobalKeyListenerObserver {

    void updatePressEvent(NativeKeyEvent evt);

    void updateReleaseEvent(NativeKeyEvent evt);

    void updateTypeKeyEvent(NativeKeyEvent evt);
}
