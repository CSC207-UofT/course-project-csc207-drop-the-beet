package com.planner.Controller;

import com.database.JDBCSQlite;
import com.planner.UseCases.UserManager;

import java.sql.SQLException;
import java.util.Map;

public class UserController {
//    UserManager user;
//    Map<Integer, UserManager> users;
//
//    public UserController(String userName, String email, String password) {
//        this.user = new UserManager(userName, email, password);
//        JDBCSQlite jdbcsQlite = new JDBCSQlite();
//        jdbcsQlite.create();
//        try{
//            Integer userID = jdbcsQlite.getUserIDByUserName(userName);
//            this.users.put(userID, this.user);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        jdbcsQlite.close();
//
//    }

    public static void loadModifyUser(UserManager currUser) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try{
            currUser.setEmail(jdbcsQlite.getUserEmail(currUser.getName()));
            currUser.setPassword(jdbcsQlite.getUserPassword(currUser.getName()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        jdbcsQlite.close();
    }
}
