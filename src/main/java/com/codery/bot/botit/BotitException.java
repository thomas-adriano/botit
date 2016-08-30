package com.codery.bot.botit;

/**
 * Botit root exception. Should be the only exception seen from the outside world.
 */
public class BotitException extends RuntimeException {

    public BotitException() {
        super();
    }

    public BotitException(String message) {
        super(message);
    }

    public BotitException(String message, Throwable cause) {
        super(message, cause);
    }

    public BotitException(Throwable cause) {
        super(cause);
    }

    protected BotitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
