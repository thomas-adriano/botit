package com.codery.bot.botit;

public enum EventTypes {

    LEFT_CLICK("L_CLICK", 1), RIGHT_CLICK("R_CLICK", 2), MIDDLE_CLICK("M_CLICK", 3), F1("F1", 00), F2("F2", 00),
    F3("F3", 00), F4("F4", 00), F5("F5", 00), F6("F6", 00), F7("F7", 00), F8("F8", 00), F9("F9", 00), F10("F10", 00),
    F11("F11", 00), F12("F12", 00), _1("1", 00), _2("2", 00), _3("3", 00), _4("4", 00), _5("5", 00), _6("6", 00),
    _7("7", 00), _8("8", 00), _9("9", 00), _0("0", 00);

    private final String value;
    private final int nativeEventCode;

    EventTypes(String value, int nativeEventCode) {
        this.value = value;
        this.nativeEventCode = nativeEventCode;
    }

    public String value() {
        return value;
    }

    public int getNativeEventCode() {
        return nativeEventCode;
    }

    public static EventTypes fromString(String evt) {
        for (EventTypes each : values()) {
            if (each.value.equalsIgnoreCase(evt)) {
                return each;
            }
        }

        throw new IllegalArgumentException("Event " + evt + " not supported.");
    }

    public static EventTypes fromNativeEventCode(int code) {
        for (EventTypes each : values()) {
            if (each.nativeEventCode == code) {
                return each;
            }
        }

        throw new IllegalArgumentException("Native code " + code + " not supported.");
    }

}