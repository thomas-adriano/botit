package com.codery.bot.botit.actions;

import java.awt.event.KeyEvent;

public enum EventKeys {

    LEFT_CLICK("L_CLICK", 1), //
    RIGHT_CLICK("R_CLICK", 2), //
    MIDDLE_CLICK("M_CLICK", 3), //
    F1("F1", KeyEvent.VK_F1), //
    F2("F2", KeyEvent.VK_F2),//
    F3("F3", KeyEvent.VK_F3), //
    F4("F4", KeyEvent.VK_F4), //
    F5("F5", KeyEvent.VK_F5), //
    F6("F6", KeyEvent.VK_F6), //
    F7("F7", KeyEvent.VK_F7),//
    F8("F8", KeyEvent.VK_F8), //
    F9("F9", KeyEvent.VK_F9), //
    F10("F10", KeyEvent.VK_F10), //
    F11("F11", KeyEvent.VK_F11),//
    _1("1", KeyEvent.VK_1), //
    _2("2", KeyEvent.VK_2), //
    _3("3", KeyEvent.VK_3), //
    _4("4", KeyEvent.VK_4), //
    _5("5", KeyEvent.VK_5), //
    _6("6", KeyEvent.VK_6), //
    _7("7", KeyEvent.VK_7), //
    _8("8", KeyEvent.VK_8),//
    _9("9", KeyEvent.VK_9),//
    _0("0", KeyEvent.VK_0);

    private final String eventDescription;
    private final int eventCode;

    EventKeys(String eventDescription, int eventCode) {
        this.eventDescription = eventDescription;
        this.eventCode = eventCode;
    }

    public String value() {
        return eventDescription;
    }

    public int getEventCode() {
        return eventCode;
    }

    public static EventKeys fromString(String evt) {
        for (EventKeys each : values()) {
            if (each.eventDescription.equalsIgnoreCase(evt)) {
                return each;
            }
        }

        throw new IllegalArgumentException("Event " + evt + " not supported.");
    }

    public static EventKeys fromCode(int code) {
        for (EventKeys each : values()) {
            if (each.eventCode == code) {
                return each;
            }
        }

        throw new IllegalArgumentException("Native code " + code + " not supported.");
    }

}