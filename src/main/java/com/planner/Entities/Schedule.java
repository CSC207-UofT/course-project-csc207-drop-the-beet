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
     * @param from is the start time in YYYY-MM-DD
     * @param to is the end time in YYYY-MM-DD
     * @param task is the title of the task
     */
    public Schedule(LocalDate from, LocalDate to, String task){
        this.start = from;
        this.end = to;
        this.task = task;
    }

    /**
     *
     * @return schedule content
     */
    public String getTask() {return this.task;}

    /**
     *
     * @return start time of this schedule
     */
    public LocalDate getStart() {return this.start;}

    /**
     *
     * @return end time of this schedule
     */
    public LocalDate getEnd() {return this.end;}
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
