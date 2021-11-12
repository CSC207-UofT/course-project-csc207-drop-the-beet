package com.planner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
/** ScheduleManager is a map of all schedules. public
 * Each Schedule has an ID
 * 这个class做成一个所有schedule的map，map的key是id，value是Schedule，可以对里面的任何一个Schedule进行操作
 */
public class ScheduleManager {
    Map<String, Schedule> schedules;
    String userName;

    public ScheduleManager() {
        this.schedules = new HashMap<String, Schedule>();

    }

    /**
     * @param start format: YYYY-MM-DD HH:MI:SS, start time of the task
     * @param end format: YYYY-MM-DD HH:MI:SS, end time of the task
     * @param task a string of task
     */
    public void addSchedule(String userName, LocalDate start, LocalDate end, String task) {
        Schedule schedule = new Schedule(start, end, task);
        this.schedules.put(userName, schedule);}

//    public void removeSchedule(String scheduleID) {this.schedules.remove(scheduleID);}
//
//    public void modifySchedule(String scheduleID, Date newStart, Date newEnd, String newTask) {
//        this.schedules.get(scheduleID).modifySchedule(newStart, newEnd, newTask);
//    }
//    public void modifyStart(HashMap<String, Schedule> schedules, String scheduleID, Date newStart) {
//        schedules.get(scheduleID).start = newStart;
//    }
//
//    public void modifyEnd(HashMap<String, Schedule> schedules, String scheduleID, Date newEnd) {
//        schedules.get(scheduleID).start = newEnd;
//    }
//
//    public void modifyStart(HashMap<String, Schedule> schedules, String scheduleID, String T) {
//        schedules.get(scheduleID).start = newStart;
//    }
}