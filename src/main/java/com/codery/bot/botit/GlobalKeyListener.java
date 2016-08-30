package com.codery.bot.botit;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.function.Consumer;

/**
 * Listen for keyboard events happening in the global screen, ignoring which application is focused.
 */
public class GlobalKeyListener implements NativeKeyListener, AutoCloseable {

    private Consumer<NativeKeyEvent> onKeyPressed;
    private Consumer<NativeKeyEvent> onKeyReleased;
    private Consumer<NativeKeyEvent> onKeyTyped;

    public void onKeyPressed(Consumer<NativeKeyEvent> c) {
        onKeyPressed = c;
    }

    public void onKeyReleased(Consumer<NativeKeyEvent> c) {
        onKeyReleased = c;
    }

    public void onKeyTyped(Consumer<NativeKeyEvent> c) {
        onKeyTyped = c;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (onKeyPressed == null) {
            return;
        }
        onKeyPressed.accept(e);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if (onKeyReleased == null) {
            return;
        }
        onKeyReleased.accept(e);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        if (onKeyTyped == null) {
            return;
        }
        onKeyTyped.accept(e);
    }

    public void start() {
        GlobalScreen.addNativeKeyListener(this);
    }

    public void close() throws Exception {
        GlobalScreen.removeNativeKeyListener(this);
        GlobalScreen.unregisterNativeHook();
    }
}