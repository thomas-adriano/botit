package com.codery.bot.botit.actions;

/**
 * Created by thomasadriano on 9/6/16.
 */
public class ActionFingerprint {

    private final EventKeys eventKey;
    private final ActionTypes actionType;

    public ActionFingerprint(EventKeys eventKey, ActionTypes actionType) {
        this.eventKey = eventKey;
        this.actionType = actionType;
    }

    public EventKeys getEventKey() {
        return eventKey;
    }

    public ActionTypes getActionType() {
        return actionType;
    }

    @Override
    public String toString() {
        return "ActionFingerprint{" +
                "eventKey=" + eventKey +
                ", actionType=" + actionType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionFingerprint that = (ActionFingerprint) o;

        if (eventKey != that.eventKey) return false;
        return actionType == that.actionType;

    }

    @Override
    public int hashCode() {
        int result = eventKey != null ? eventKey.hashCode() : 0;
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        return result;
    }
}
