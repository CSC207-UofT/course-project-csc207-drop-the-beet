package planner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ScheduleManager is to modify any of the schedules of the user.
 */
public class ScheduleManager{
    Map<String, ArrayList<Schedule>> schedules = new HashMap<>();

    /**
     * Constructor of the ScheduleManager
     */
    public ScheduleManager() {
        Schedule s = new Schedule();
        ArrayList<Schedule> list = new ArrayList<>();
        list.add(s);
        schedules.put("0000/00/00", list);
    }


    /**
     * addSchedule adds a schedule to schedules if schedules does not contain schedule
     * @param schedule is the schedule to be added
     */
    public void addSchedule(@NotNull Schedule schedule) {
        if (!this.schedules.containsKey(schedule.getDate())) {
            ArrayList<Schedule> list = new ArrayList<>();
            list.add(schedule);
            this.schedules.put(schedule.getDate(), list);
        } else {
            this.schedules.get(schedule.getDate()).add(schedule);
        }
    }

    /**
     * removeSchedule removes a schedule in schedules if it is in schedules
     * @param schedule is the schedule to be removed
     */
    public void removeSchedule(@NotNull Schedule schedule) {
        if (this.schedules.containsKey(schedule.getDate())){
            this.schedules.get(schedule.getDate()).remove(schedule);
        }
    }

    /**
     * modifyDate changes the schedule's current date with a given schedule and new date
     * @param schedule is the schedule to be modified
     * @param date is the new date
     */
    public void modifyDate(@NotNull Schedule schedule, String date) {
        schedule.setDate(date);
    }

    /**
     * modifyFrom changes the schedule's current start time with a given schedule and new start time
     * @param schedule is the schedule to be modified
     * @param from is the new start time
     */
    public void modifyFrom(@NotNull Schedule schedule, String from) {
        schedule.setFrom(from);
    }

    /**
     * modifyTo changes the schedule's current end time with a given schedule and new end time
     * @param schedule is the schedule to be modified
     * @param to is the new end time
     */
    public void modifyTo(@NotNull Schedule schedule, String to) {
        schedule.setTo(to);
    }

    /**
     * modifyTask changes the schedule's current task with a given schedule and new task
     * @param schedule is the schedule to be modified
     * @param task is the new task
     */
    public void modifyTask(@NotNull Schedule schedule, String task) {
        schedule.setTask(task);
    }


    /**
     * viewDate accesses the date of the schedule
     * @param schedule is the schedule to be checked
     * @return returns a string of the current date
     */
    public String viewDate(@NotNull Schedule schedule) {
        return schedule.getDate();
    }

    /**
     * viewFrom accesses the start time of the schedule
     * @param schedule is the schedule to be checked
     * @return returns a string of the current start time
     */
    public String viewFrom(@NotNull Schedule schedule) {
        return schedule.getFrom();
    }

    /**
     * viewTo accesses the end time of the schedule
     * @param schedule is the schedule to be checked
     * @return returns a string of the current end time
     */
    public String viewTo(@NotNull Schedule schedule) {
        return schedule.getTo();
    }

    /**
     * viewTask accesses the task of the schedule
     * @param schedule is the schedule to be checked
     * @return returns a string of the current task
     */
    public String viewTask(@NotNull Schedule schedule) {
        return schedule.getTask();
    }

    public void repeatSchedules(String info, int length) {
        // Todo
        // eg. repeat every wednesday from Feb 1st to Jul 15th
    }

    public void deleteRepeatSchedules(String info, int length) {
        // Todo
    }

    // Todo Notification in ScheduleManager or in Schedule
    public static void main(String[] args) {
//        Map<String, ArrayList<String>> m = new HashMap<>();
//        ArrayList<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        m.put("a", list);
//        System.out.println(list);
//        System.out.println(m.containsKey("a"));
//        System.out.println(m.values());
//        System.out.println(m.get("a").remove("2"));
//        System.out.println(m.values());
//        System.out.println(m);
//        System.out.println(m.get("a").isEmpty());

        Schedule a = new Schedule("2021/10/14", " 11:00", "12:00", "I need to cook");
        System.out.println(a);
        ScheduleManager b = new ScheduleManager();
        b.addSchedule(a);
        System.out.println(b.schedules.get("2021/10/14"));
        b.removeSchedule(a);
        System.out.println(b.schedules.get("2021/10/14").isEmpty());

    }
}