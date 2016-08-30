package com.codery.bot.botit.nativeevents;

import com.codery.bot.botit.BotitException;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.function.BiConsumer;

/**
 * Provide ways to listen to mouse or keyboard events.
 */
public class GlobalEventListener {

    /**
     * Provide an environment to handle mouse and keyboard events. The method's consumer/client does not need to worry
     * with freeing resources or native hooks registration.
     *
     * @param c the logic to be executed with the mouse/keyboard events listeners
     */
    public static void whileListeningForMouseAndKeyEventsDo(BiConsumer<GlobalMouseListener, GlobalKeyListener> c) {
        try (GlobalMouseListener gml = new GlobalMouseListener(); GlobalKeyListener gkl = new GlobalKeyListener()) {
            GlobalScreen.registerNativeHook();
            c.accept(gml, gkl);
        } catch (Exception e) {
            throw new BotitException("An error occurred while listening for mouse events", e);
        } finally {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                throw new BotitException("Unable to unregister GlobalScreen native hook", e1);
            }
        }
    }


}
