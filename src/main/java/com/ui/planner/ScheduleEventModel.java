package com.ui.planner;

import javafx.beans.property.SimpleStringProperty;

public class ScheduleEventModel {
    private final SimpleStringProperty event;
    private final SimpleStringProperty start;
    private final SimpleStringProperty end;

    /**
     * constructor of ScheduleEventModel
     * @param event event content
     * @param start start time of event
     * @param end end time of event
     */
    public ScheduleEventModel(String event, String start, String end) {
        this.start = new SimpleStringProperty(start);
        this.event = new SimpleStringProperty(event);
        this.end =  new SimpleStringProperty(end);
    }

    /**
     * get the SimpleStringProperty of this event
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
     * get SimpleStringProperty of the start time
     * @return SimpleStringProperty of the start time
     */
    public String getStart() {
        return start.get();
    }

    /**
     * set SimpleStringProperty of the start time
     * @param start SimpleStringProperty of the start time
     */
    public void setStart(String start) {
        this.start.set(start);
    }

    /**
     * get SimpleStringProperty of the end time
     * @return SimpleStringProperty of the start time
     */
    public String getEnd() {
        return end.get();
    }

    /**
     * set SimpleStringProperty of the start time
     * @param end SimpleStringProperty of the start time
     */
    public void setEnd(String end) {
        this.end.set(end);
    }
}
