package com.planner.Entities;

import java.time.LocalDate;

/** Schedule is a single task with start time and end time.
 */

public class Schedule{
    private String task;
    private LocalDate start;
    private LocalDate end;
//    private ArrayList<Object> repeat = new ArrayList<>();

    /**
     * Constructor of the Schedule
     * @param from is the start time in HH:mm (24hrs)
     * @param to is the end time in HH:mm (24hrs)
     * @param task is the title of the task
     */
    public Schedule(LocalDate from, LocalDate to, String task){
        this.start = from;
        this.end = to;
        this.task = task;
    }

//    /**
//     * This method updates a new date
//     * @param date is the new date
//     */
//    public void setDate(String date) {
//        this.date = date;
//    }

    @Override
    public String toString() {
        return this.task;
    }

}
