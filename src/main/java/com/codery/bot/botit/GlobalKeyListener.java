package com.codery.bot.botit;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.HashSet;
import java.util.Set;

public class GlobalKeyListener implements NativeKeyListener, AutoCloseable {

    private final Set<GlobalKeyListenerObserver> observables = new HashSet<>();

    public void registerObserver(GlobalKeyListenerObserver o) {
        observables.add(o);
    }

    public void notifyObserversPress(NativeKeyEvent evt) {
        observables.forEach((o) -> o.updatePressEvent(evt));
    }

    public void notifyObserversRelease(NativeKeyEvent evt) {
        observables.forEach((o) -> o.updateReleaseEvent(evt));
    }

    public void notifyObserversType(NativeKeyEvent evt) {
        observables.forEach((o) -> o.updateTypeKeyEvent(evt));
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        notifyObserversPress(e);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        notifyObserversRelease(e);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        notifyObserversType(e);
    }

    public void start() {
        GlobalScreen.addNativeKeyListener(this);
    }

    public void close() throws Exception {
        GlobalScreen.removeNativeKeyListener(this);
        GlobalScreen.unregisterNativeHook();
    }
}