package com.codery.bot.botit.nativeevents;

import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.util.function.Consumer;

/**
 * Listen for mouse events happening in the global screen, ignoring which application is focused.
 */
public class GlobalMouseListener implements NativeMouseInputListener, AutoCloseable {

    private Consumer<NativeMouseEvent> onMouseReleased;
    private Consumer<NativeMouseEvent> onMouseMoved;
    private Consumer<NativeMouseEvent> onMouseDragged;
    private Consumer<NativeMouseEvent> onMouseClicked;
    private Consumer<NativeMouseEvent> onMousePressed;

    public void onMouseReleased(Consumer<NativeMouseEvent> c) {
        onMouseReleased = c;
    }

    public void onMouseClicked(Consumer<NativeMouseEvent> c) {
        onMouseClicked = c;
    }

    public void onMousePressed(Consumer<NativeMouseEvent> c) {
        onMousePressed = c;
    }

    public void onMouseMoved(Consumer<NativeMouseEvent> c) {
        onMouseMoved = c;
    }

    public void onMouseDragged(Consumer<NativeMouseEvent> c) {
        onMouseDragged = c;
    }


    public void nativeMouseClicked(NativeMouseEvent e) {
        if (onMouseClicked == null) {
            return;
        }
        onMouseClicked.accept(e);
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        if (onMousePressed == null) {
            return;
        }
        onMousePressed.accept(e);
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        if (onMouseReleased == null) {
            return;
        }
        onMouseReleased.accept(e);
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        if (onMouseMoved == null) {
            return;
        }
        onMouseMoved.accept(e);
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        if (onMouseDragged == null) {
            return;
        }
        onMouseDragged.accept(e);
    }

    public void start() {
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);
    }

    public void close() throws Exception {
        GlobalScreen.removeNativeMouseListener(this);
        GlobalScreen.removeNativeMouseMotionListener(this);
    }
}