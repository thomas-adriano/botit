package com.codery.bot.botit;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by thomasadriano on 8/29/16.
 */
public class GlobalEventListener {

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
