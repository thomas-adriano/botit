package com.codery.bot.botit;

/**
 * Created by Thomas.Adriano on 05/09/2016.
 */
public class Constraint {

    private final EventTypes evt;
    private final int quantity;
    private final Measure measure;

    public Constraint(EventTypes evt, int quantity, Measure measure) {
        this.evt = evt;
        this.quantity = quantity;
        this.measure = measure;
    }

    public EventTypes getEvt() {
        return evt;
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
                "evt=" + evt +
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
        if (evt != that.evt) return false;
        return measure == that.measure;

    }

    @Override
    public int hashCode() {
        int result = evt != null ? evt.hashCode() : 0;
        result = 31 * result + quantity;
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        return result;
    }
}
