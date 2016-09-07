package com.codery.bot.botit;

/**
 * Created by thomas on 07/09/2016.
 */
public class Measure {

    private final int quantity;
    private final MeasureUnit measureUnit;

    private Measure(int quantity, MeasureUnit measureUnit) {
        this.quantity = quantity;
        this.measureUnit = measureUnit;
    }

    public static final Measure times(int quantity) {
        return new Measure(quantity, MeasureUnit.TIMES);
    }

    public int getQuantity() {
        return quantity;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }
}

