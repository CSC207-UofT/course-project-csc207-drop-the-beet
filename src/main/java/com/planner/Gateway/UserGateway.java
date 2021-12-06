package com.planner.Gateway;

import com.database.DBUser;
import com.planner.UseCases.UserManager;

import java.sql.SQLException;


public class UserGateway {

    public static UserManager loadAllUserInfo(String username) {
        DBUser jdbcsQlite = new DBUser();
        jdbcsQlite.create();
        try{
            String password = jdbcsQlite.getUserPassword(username);
            String email = jdbcsQlite.getUserEmail(username);
            UserManager userManager = new UserManager(username, email, password);
            userManager.setSchedules(SchedulesGateway.getAllSchedule(username));
            userManager.setToDoLists(ToDoListsGateway.getAllToDoLists(username));
            userManager.setImportant(ImportantGateway.getAllImportant(username));

            jdbcsQlite.close();
            return userManager;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            jdbcsQlite.close();
            return null;
        }
    }

    public static void writeAllUserInfo(UserManager user) {
        DBUser jdbcsQlite = new DBUser();
        jdbcsQlite.create();
        try {
            SchedulesGateway.writeAllSchedule(user);
            ToDoListsGateway.writeAllToDoList(user);
            ImportantGateway.writeAllImportant(user);
            jdbcsQlite.changeUserEmailByUserName(user.getName(), user.getEmail());
            jdbcsQlite.changeUserPasswordByUserName(user.getName(), user.getPassword());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }


}
