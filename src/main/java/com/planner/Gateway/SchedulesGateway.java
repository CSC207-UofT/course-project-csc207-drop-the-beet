package com.planner.Gateway;

import com.database.JDBCSQlite;
import com.planner.UseCases.ScheduleManager;
import com.planner.UseCases.UserManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SchedulesGateway {

    public static ScheduleManager getAllSchedule(String username) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try{
            List<List<String>> schedules = jdbcsQlite.getAllUserEventTasksByUserName(username);
            ScheduleManager scheduleManager = new ScheduleManager();
            System.out.println(schedules);
            if (schedules == null) {
                schedules = new ArrayList<>();
            }
            scheduleManager.setSchedules(schedules);
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
            if (schedules != null) {
                for (List<String> schedule : schedules) {
                    int id = Integer.parseInt(schedule.get(0));
                    jdbcsQlite.deleteUserEventListByTaskID(id);
                }
                List<List<String>> scheduleWrite = user.getSchedules().getScheduleLists();
                for (List<String> schedule : scheduleWrite) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate start = LocalDate.parse(schedule.get(1), formatter);
                    LocalDate end = LocalDate.parse(schedule.get(2), formatter);
                    jdbcsQlite.createEventTaskByUserName(user.getName(), schedule.get(0), start, end);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }

}
