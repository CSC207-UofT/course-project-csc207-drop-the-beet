package com.planner.Gateway;

import com.database.JDBCSQlite;
import com.planner.Entities.Schedule;
import com.planner.UseCases.ScheduleManager;
import com.planner.UseCases.UserManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SchedulesGateway {

    public static ScheduleManager getAllSchedule(String username) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            List<List<String>> schedules = jdbcsQlite.getAllUserEventTasksByUserName(username);
            ScheduleManager scheduleManager = new ScheduleManager();
            System.out.println(schedules);
            for (List<String> schedule : schedules) {
                String task = schedule.get(3);
                System.out.println(schedule.get(4));
                System.out.println(schedule.get(5));
                LocalDate start = LocalDate.parse(schedule.get(4), formatter);
                LocalDate end = LocalDate.parse(schedule.get(5), formatter);
                scheduleManager.addSchedule(start, end, task);
            }

            return scheduleManager;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeAllSchedule(UserManager user) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try {
            List<List<String>> schedules = jdbcsQlite.getAllUserEventTasksByUserName(user.getName());
            for (List<String> schedule : schedules) {
                int id = Integer.parseInt(schedule.get(0));
                jdbcsQlite.deleteUserEventListByTaskID(id);
            }
            List<Schedule> scheduleWrite = user.getSchedules().getSchedules();
            for (Schedule schedule : scheduleWrite) {
                jdbcsQlite.createEventTaskByUserName(user.getName(), schedule.getTask(), schedule.getStart(), schedule.getEnd());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }

}
