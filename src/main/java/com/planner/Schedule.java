package com.planner;

/** Schedule is a single task with start time and end time.
 */

public class Schedule{
    private String date;
    private String task;
    private String start;
    private String end;
//    private ArrayList<Object> repeat = new ArrayList<>();

    /**
     * Constructor of the Schedule
     * @param date is the day of the schedule in yyyy/MM/dd
     * @param from is the start time in HH:mm (24hrs)
     * @param to is the end time in HH:mm (24hrs)
     * @param task is the title of the task
     */
    public Schedule(String date, String from, String to, String task){
        this.date = date;
        this.start = from;
        this.end = to;
        this.task = task;
    }

    /**
     * This method returns the current date
     * @return this schedules' date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * This method updates a new date
     * @param date is the new date
     */
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.task;
    }

}
