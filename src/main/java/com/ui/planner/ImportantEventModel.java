package com.ui.planner;

import javafx.beans.property.SimpleStringProperty;

public class ImportantEventModel {
    private final SimpleStringProperty event;
    private final SimpleStringProperty start;
    private final SimpleStringProperty end;

    /**
     * Constructor of ImportantEventModel
     * @param event event content
     * @param start start time event
     * @param end end time of the event
     */
    public ImportantEventModel(String event, String start, String end) {
        this.start = new SimpleStringProperty(start);
        this.event = new SimpleStringProperty(event);
        this.end =  new SimpleStringProperty(end);
    }

    /**
     * get SimpleStringProperty of event
     * @return SimpleStringProperty of event
     */
    public String getEvent() {
        return event.get();
    }

    /**
     * set SimpleStringProperty of event
     * @param event SimpleStringProperty of event
     */
    public void setEvent(String event) {
        this.event.set(event);
    }

    /**
     * get SimpleStringProperty of start time
     * @return SimpleStringProperty of start time
     */
    public String getStart() {
        return start.get();
    }

    /**
     * set SimpleStringProperty of start time
     * @param start SimpleStringProperty of start time
     */
    public void setStart(String start) {
        this.start.set(start);
    }

    /**
     * get SimpleStringProperty of end time
     * @return SimpleStringProperty of end time
     */
    public String getEnd() {
        return end.get();
    }

    /**
     * set SimpleStringProperty of end time
     * @param end SimpleStringProperty of end time
     */
    public void setEnd(String end) {
        this.end.set(end);
    }
}
