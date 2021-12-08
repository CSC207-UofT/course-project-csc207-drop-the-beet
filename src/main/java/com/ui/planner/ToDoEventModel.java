package com.ui.planner;

import javafx.beans.property.SimpleStringProperty;

public class ToDoEventModel {
    private final SimpleStringProperty event;
    private final SimpleStringProperty end;

    /**
     * Event model of ToDoList
     * @param event event content
     * @param end deadline of the event
     */
    public ToDoEventModel(String event,  String end) {
        this.event = new SimpleStringProperty(event);
        this.end =  new SimpleStringProperty(end);
    }

    /**
     * get SimpleStringProperty of this event
     * @return SimpleStringProperty of this event
     */
    public String getEvent() {
        return event.get();
    }

    /**
     * set SimpleStringProperty of this event
     * @param event SimpleStringProperty of this event
     */
    public void setEvent(String event) {
        this.event.set(event);
    }

    /**
     * get SimpleStringProperty of this deadline
     * @return SimpleStringProperty of this deadline
     */
    public String getEnd() {
        return end.get();
    }

    /**
     * set SimpleStringProperty of this deadline
     * @param end SimpleStringProperty of this deadline
     */
    public void setEnd(String end) {
        this.end.set(end);
    }
}
