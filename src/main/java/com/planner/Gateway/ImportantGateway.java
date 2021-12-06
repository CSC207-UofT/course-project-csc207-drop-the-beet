package com.planner.Gateway;

import com.database.*;
import com.planner.Entities.Schedule;
import com.planner.UseCases.ScheduleManager;
import com.planner.UseCases.UserManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ImportantGateway {

    public static ScheduleManager getAllImportant(String username) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            List<List<String>> importants = jdbcsQlite.getAllUserImportantTasksByUserName(username);
            ScheduleManager importantManager = new ScheduleManager();
            for (List<String> important : importants) {
                String task = important.get(3);
                LocalDate start = LocalDate.parse(important.get(4), formatter);
                LocalDate end = LocalDate.parse(important.get(5), formatter);
                importantManager.addSchedule(start, end, task);
            }
            return importantManager;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeAllImportant(UserManager user) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try {
            List<List<String>> importants = jdbcsQlite.getAllUserImportantTasksByUserName(user.getName());
            for (List<String> important : importants) {
                int id = Integer.parseInt(important.get(0));
                jdbcsQlite.deleteUserImportantListByTaskID(id);
            }
            List<List<String>> importantWrite = user.getImportant().getScheduleLists();
            for (List<String> important : importantWrite) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate start = LocalDate.parse(important.get(1), formatter);
                LocalDate end = LocalDate.parse(important.get(2), formatter);
                jdbcsQlite.createImportantTaskByUserName(user.getName(), important.get(0), start, end);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }
}
