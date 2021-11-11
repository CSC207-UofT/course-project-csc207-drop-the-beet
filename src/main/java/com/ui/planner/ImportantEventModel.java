package com.ui.planner;

import javafx.beans.property.SimpleStringProperty;

public class ImportantEventModel {
    private  SimpleStringProperty event;
    private  SimpleStringProperty start;
    private  SimpleStringProperty end;

    public ImportantEventModel(String event, String start, String end) {
        this.start = new SimpleStringProperty(start);
        this.event = new SimpleStringProperty(event);
        this.end =  new SimpleStringProperty(end);
    }

    public String getEvent() {
        return event.get();
    }

    public void setEvent(String event) {
        this.event.set(event);
    }

    public String getStart() {
        return start.get();
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public String getEnd() {
        return end.get();
    }

    public void setEnd(String end) {
        this.end.set(end);
    }
}