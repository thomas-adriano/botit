package com.codery.bot.botit;

import com.codery.bot.botit.actions.ActionFingerprint;

/**
 * Created by Thomas.Adriano on 05/09/2016.
 */
public class Constraint {

    private final ActionFingerprint actionFingerprint;
    private final int quantity;
    private final Measure measure;

    public Constraint(ActionFingerprint actionFingerprint, int quantity, Measure measure) {
        this.actionFingerprint = actionFingerprint;
        this.quantity = quantity;
        this.measure = measure;
    }

    public ActionFingerprint getActionFingerprint() {
        return actionFingerprint;
    }

    public int getQuantity() {
        return quantity;
    }

    public Measure getMeasure() {
        return measure;
    }

    @Override
    public String toString() {
        return "Constraint{" +
                "actionFingerprint=" + actionFingerprint +
                ", quantity=" + quantity +
                ", measure=" + measure +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constraint that = (Constraint) o;

        if (quantity != that.quantity) return false;
        if (actionFingerprint != that.actionFingerprint) return false;
        return measure == that.measure;

    }

    @Override
    public int hashCode() {
        int result = actionFingerprint != null ? actionFingerprint.hashCode() : 0;
        result = 31 * result + quantity;
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        return result;
    }
}
