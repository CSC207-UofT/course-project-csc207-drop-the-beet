package com.planner.Controller;

import com.database.*;
import com.planner.UseCases.UserManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class ImportantController {

    /**
     * important
     */

    public static ArrayList<ArrayList<String>> loadImportant(UserManager user) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        ArrayList<ArrayList<String>> lst = null;
        try{
            lst = jdbcsQlite.getAllUserImportantTasksByUserName(user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcsQlite.close();
        return lst;
    }

    public static int loadImportantBubble (UserManager currUser, JDBCSQlite jdbcsQlite) {
        int importantNum = 0;
        try {
            ArrayList<ArrayList<String>> allUserImportantTasks = jdbcsQlite.getAllUserImportantTasksByUserName(currUser.getName());

            if (allUserImportantTasks != null) {
                importantNum = allUserImportantTasks.size();
            }
            return importantNum;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return importantNum;
    }
}
