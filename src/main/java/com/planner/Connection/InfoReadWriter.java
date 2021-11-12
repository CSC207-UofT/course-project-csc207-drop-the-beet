package com.planner.Connection;

import com.ui.planner.SignupController;
import com.datebase.JDBCSQlite;
import com.planner.UserManager;
import javafx.scene.control.Alert;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class InfoReadWriter {

    //应该会分成3个class
    //below are all for users

//    public static void userWrite(JDBCSQlite jdbcsQlite) {
//
//        if (SverifyPassword() && !jdbcsQlite.isUserNameExist(getUserName())) {
//            jdbcsQlite.createNewUser(getUserName(), getEmail(), getPassword());
//            signUpSuccessHandler();
//
//        } else {
//
//        } //Todo 52 69  都是private 写不了
//        jdbcsQlite.close();
//    }
//
//    public static void modifyEmail(String userID, String email) {
//        // Todo open the user file,
//        //  call the methods in UserManager, and change the email, then write into database
//        //  stores all info in database,
//        //  close the file
//    }
//
//    public static void modifyPassword(String userID, String password) {
//        // Todo call the methods in UserManager, and change the password, then write into database
//    }
//
//    public static String checkUsername(String username) {
//        return "null"; //Todo null if exsits, otherwise return a new id;
//    }
//
//


//    public static void loadModifyUser(UserManager currUser) {
//        JDBCSQlite jdbcsQlite = new JDBCSQlite();
//        jdbcsQlite.create();
//        try{
//            currUser.setEmail(jdbcsQlite.getUserEmail(currUser.getName()));
//            currUser.setPassword(jdbcsQlite.getUserPassword(currUser.getName()));
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//        jdbcsQlite.close();
//    }
//
//
//    //below are all for schedules
//    /**
//     * @param start format: YYYY-MM-DD HH:MI:SS, start time of the task
//     * @param end format: YYYY-MM-DD HH:MI:SS, end time of the task
//     * @param task a string of task
//     */
//    public static void newScheduleWrite(UserManager user, LocalDate start, LocalDate end, String task) {
//        JDBCSQlite jdbcsQlite = new JDBCSQlite();
//        jdbcsQlite.create();
//        try{
//
//            jdbcsQlite.createEventTaskByUserName(user.getName(), task, start, end);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void modifySDWrite(String userID, String scheduleID, Date start, Date end, String task) {
//        // Todo open the Schedules file for this userID,
//        //  read the database to find the schedule,
//        //  call modifySchedule in ScheduleManager, write change in database,
//        //  close the file
//    }
//
//    public static void deleteSDWrite(String scheduleID, String end) {
//        // Todo open the Schedules file for this userID,
//        //  read the database to find the schedule and delete the schedule in database,
//        //  close the file
//    }

//    public static int loadScheduleBubble (UserManager currUser, JDBCSQlite jdbcsQlite) {
//        int scheduleNum = 0;
//        try {
//            ArrayList<ArrayList<String>> allUserSchedules = jdbcsQlite.getAllUserEventTasksByUserName(currUser.getName());
//
//            if (allUserSchedules != null) {
//                scheduleNum = allUserSchedules.size();
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//        return scheduleNum;
//    }


    //below are all for todolist

//    public static void newTDWrite(String userID, String listName) {
//        // Todo open the ToDoLists file inside of the userID,
//        //  create a new ToDoList ID and call addList in ToDoListManage to create ToDoList
//        //  and write the info in the database,
//        //  close the file
//    }
//
//    public static void modifyTDWrite(String userID, String listID, String newListName) {
//        // Todo open the ToDoLists file for this userID,
//        //  read the database to find the ToDoList,
//        //  call modifyListName in ToDoListManager, write change in database,
//        //  close the file
//    }
//
//    public static void deleteTDWrite(String scheduleID, String end) {
//        // Todo open the ToDoLists file for this userID,
//        //  read the database to find the ToDoList and delete the ToDoList in database
//    }
//
//    public static void newTaskWrite(String userID, String listID, String task) {
//        // Todo open the file inside the todolist,
//        //  create a new task, write in database, close the file
//    }
//
//    public static void modifyTaskWrite(String userID, String listID, String taskID, String task) {
//        // Todo open the file inside the todolist,
//        //  modify the task, write in database, close the file
//    }
//
//    public static void deleteTaskWrite(String userID, String listID, String taskID) {
//        // Todo open the file inside the todolist,
//        //  delete the task, write in database, close the file
//    }
//
//    public static void completeTask(String userID, String listID, String taskID) {
//        // Todo
//    }
//    public static ArrayList<ArrayList<String>> loadToDo(UserManager user) {
//        JDBCSQlite jdbcsQlite = new JDBCSQlite();
//        jdbcsQlite.create();
//        ArrayList<ArrayList<String>> lst = null;
//        try{
//            lst = jdbcsQlite.getAllUserToDoTasksByUserName(user.getName());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        jdbcsQlite.close();
//        return lst;
//    }

//    public static int loadTodoBubble(UserManager currUser, JDBCSQlite jdbcsQlite) {
//        int todoNum = 0;
//        try {
//            ArrayList<ArrayList<String>> allUserToDoTasks = jdbcsQlite.getAllUserToDoTasksByUserName(currUser.getName());
//
//            if (allUserToDoTasks != null) {
//                todoNum = allUserToDoTasks.size();
//            }
//            return todoNum;
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return todoNum;
//    }

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
