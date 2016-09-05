package com.codery.bot.botit;

public enum Actions {

    WALK("WALK"), F1("F1"), F2("F2"), F3("F3"), F4("F4"), F5("F5"), F6("F6"), F7("F7"), F8("F8"), F9("F9"), F10("F10"), F11("F11"), F12("F12"),
    _1("1"), _2("2"), _3("3"), _4("4"), _5("5"), _6("6"), _7("7"), _8("8"), _9("9"), _0("0");

    private final String value;

    Actions(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    static Actions fromString(String act) {
        for (Actions each : values()) {
            if (each.value.equalsIgnoreCase(act)) {
                return each;
            }
        }

        throw new IllegalArgumentException("Action "+act+" not supported.");
    }

}