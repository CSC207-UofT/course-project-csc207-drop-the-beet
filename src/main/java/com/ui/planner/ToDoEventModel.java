package com.ui.planner;

import javafx.beans.property.SimpleStringProperty;

public class ToDoEventModel {
    private SimpleStringProperty event;
    private  SimpleStringProperty end;

    public ToDoEventModel(String event,  String end) {
        this.event = new SimpleStringProperty(event);
        this.end =  new SimpleStringProperty(end);
    }

    public String getEvent() {
        return event.get();
    }

    public void setEvent(String event) {
        this.event.set(event);
    }

    public String getEnd() {
        return end.get();
    }

    public void setEnd(String end) {
        this.end.set(end);
    }
}
