package com.planner.Controller;

import com.database.*;
import com.planner.UseCases.UserManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ImportantController {

    /**
     * important
     */

    /**
     *
     * @param user the login userManager
     * @return a list
     */
    public static List<List<String>> loadImportant(UserManager user) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        List<List<String>> lst = null;
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
            List<List<String>> allUserImportantTasks = jdbcsQlite.getAllUserImportantTasksByUserName(currUser.getName());

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
