package com.planner.Controller;

import com.database.JDBCSQlite;
import com.planner.UseCases.UserManager;

import java.sql.SQLException;


public class UserController {


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
