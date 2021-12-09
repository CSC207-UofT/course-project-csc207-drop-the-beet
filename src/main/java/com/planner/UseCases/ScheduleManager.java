package com.planner.UseCases;

import com.planner.Entities.Schedule;
import com.planner.Entities.ToDoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    /**
     * set schedules from giving list
     * @param schedulesLists a list of string lists, string list format [todoID, userID, task, start, end]
     */
    public void setSchedules(List<List<String>> schedulesLists) {
        List<Schedule> schedules = new ArrayList<>();
        for (List<String> l : schedulesLists) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String task = l.get(3);
            LocalDate start = LocalDate.parse(l.get(4), formatter);
            LocalDate end = LocalDate.parse(l.get(5), formatter);
            Schedule schedule = new Schedule(start, end, task);
            schedules.add(schedule);
        }
        this.schedules = schedules;
    }

    /**
     *
     * @return a list of string lists, string list format [task, start, end]
     */
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

    /**
     *
     * @return size of schedules
     */
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

    public static void main(String[] args) {
        ScheduleManager sm = new ScheduleManager();

        List<List<String>> bigList = new ArrayList<>();
        List<String> smallList1 = new ArrayList<>();
        List<String> smallList2 = new ArrayList<>();
        smallList1.add("todoID1");
        smallList1.add("userID1");
        smallList1.add("Sleep");
        smallList1.add("2020-01-01");
        smallList1.add("2021-01-01");

        smallList2.add("todoID1");
        smallList2.add("userID1");
        smallList2.add("Eat");
        smallList2.add("2020-01-01");
        smallList2.add("2021-01-01");

        bigList.add(smallList1);
        bigList.add(smallList2);
        sm.setSchedules(bigList);
        System.out.println(sm.getSize());
        System.out.println(sm.getScheduleLists());
    }

}