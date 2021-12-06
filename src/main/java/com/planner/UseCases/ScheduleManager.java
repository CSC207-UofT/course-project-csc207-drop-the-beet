package com.planner.UseCases;

import com.planner.Entities.Schedule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** ScheduleManager is a map of all schedules. public
 * Each Schedule has an ID
 * 这个class做成一个所有schedule的map，map的key是id，value是Schedule，可以对里面的任何一个Schedule进行操作
 */
public class ScheduleManager {
    List<Schedule> schedules;


    /**
     * constructor of ScheduleManager
     */
    public ScheduleManager() {
        this.schedules = new ArrayList<>();

    }

//    public List<Schedule> getSchedules() {
//        return this.schedules;
//    }

    public List<List<String>> getScheduleLists() {
        List<List<String>> scheduleLists = new ArrayList<>();
        for (Schedule schedule : this.schedules) {
            List<String> scheduleList = new ArrayList<>();
            scheduleList.add(schedule.getTask());
            scheduleList.add(schedule.getStart().toString());
            scheduleList.add(schedule.getEnd().toString());
            scheduleLists.add(scheduleList);
        }
        return scheduleLists;
    }

    public int getSize() {
        return this.schedules.size();
    }

    /**
     * @param start format: YYYY-MM-DD HH:MI:SS, start time of the task
     * @param end format: YYYY-MM-DD HH:MI:SS, end time of the task
     * @param task a string of task
     */
    public void addSchedule(LocalDate start, LocalDate end, String task) {
        Schedule schedule = new Schedule(start, end, task);
        this.schedules.add(schedule);}

//    public void removeSchedule(String scheduleID) {this.schedules.remove(scheduleID);}
//
//    public void modifySchedule(String scheduleID, LocalDate newStart, LocalDate newEnd, String newTask) {
//        this.schedules.get(scheduleID).modifySchedule(newStart, newEnd, newTask);
//    }
//    public void modifyStart(HashMap<String, Schedule> schedules, String scheduleID, LocalDate newStart) {
//        schedules.get(scheduleID).start = newStart;
//    }
//
//    public void modifyEnd(HashMap<String, Schedule> schedules, String scheduleID, LocalDate newEnd) {
//        schedules.get(scheduleID).start = newEnd;
//    }
//
//    public void modifyStart(HashMap<String, Schedule> schedules, String scheduleID, String T) {
//        schedules.get(scheduleID).start = newStart;
//    }

}