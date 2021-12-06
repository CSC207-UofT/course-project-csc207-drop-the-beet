package com.planner.Gateway;

import com.database.JDBCSQlite;
import com.planner.UseCases.ScheduleManager;
import com.planner.UseCases.UserManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ImportantGateway {

    public static ScheduleManager getAllImportant(String username) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();

        try{
            List<List<String>> importants = jdbcsQlite.getAllUserImportantTasksByUserName(username);
            ScheduleManager importantManager = new ScheduleManager();
            if (importants == null) {
                importants = new ArrayList<>();
            }
            importantManager.setSchedules(importants);
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
            if (importants != null) {
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
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }
}
