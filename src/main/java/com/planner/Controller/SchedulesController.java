package com.planner.Controller;

import com.database.JDBCSQlite;
import com.planner.UseCases.UserManager;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SchedulesController {

    public static void newScheduleWrite(UserManager user, LocalDate start, LocalDate end, String task) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try{
            user.getSchedules().addSchedule(user.getName(),start,end,task);
            jdbcsQlite.createEventTaskByUserName(user.getName(), task, start, end);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifySchedule(Date newStart, Date newEnd, String newTask) {

    }

    public static void deleteSchedule(String scheduleID) {

    }

    public static int loadScheduleBubble (UserManager currUser, JDBCSQlite jdbcsQlite) {
        int scheduleNum = 0;
        try {
            List<List<String>> allUserSchedules = jdbcsQlite.getAllUserEventTasksByUserName(currUser.getName());

            if (allUserSchedules != null) {
                scheduleNum = allUserSchedules.size();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return scheduleNum;
    }
}
