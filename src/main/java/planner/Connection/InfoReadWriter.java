package planner.Connection;

import com.sun.jdi.ArrayReference;
import planner.UserManager;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class InfoReadWriter {

    //应该会分成3个class
    //below are all for users
    /**
     * @param username registered username of the user
     * @param email registered email
     * @param password registered password
     */

    public static void userWrite(String userID, String username, String email, String password) {
        // Todo open the user file,
        //  call the methods in UserManager, initialize ScheduleManager and ToDoListManager,
        //  stores all info in database,
        //  close the file
    }

    public static void modifyEmail(String userID, String email) {
        // Todo open the user file,
        //  call the methods in UserManager, and change the email, then write into database
        //  stores all info in database,
        //  close the file
    }

    public static void modifyPassword(String userID, String password) {
        // Todo call the methods in UserManager, and change the password, then write into database
    }

    public static String checkUsername(String username) {
        return "null"; //Todo null if exsits, otherwise return a new id;
    }

    /**
     * @param username registered username of the user
     * @return ArrayList [userID, username, email, password]
     */
    public static ArrayList<String> readInfo(String username) {
        return;
        // Todo open the user file,
        //  read all personal info
        //  close the file
    }


    //below are all for schedules
    /**
     * @param start format: YYYY-MM-DD HH:MI:SS, start time of the task
     * @param end format: YYYY-MM-DD HH:MI:SS, end time of the task
     * @param task a string of task
     */
    public static void newSDWrite(String userName, Date start, Date end, String task) {
        // Todo open the Schedules file inside of the userID,
        //  create a new schedule ID and call addNewSchedule in ScheduleManager to create schedule
        //  and write the info in the database,
        //  close the file

        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try{
            jdbcsQlite.createEventTaskByUserName(user.getName(), event, startDateInput, endingDateInput);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifySDWrite(String userID, String scheduleID, Date start, Date end, String task) {
        // Todo open the Schedules file for this userID,
        //  read the database to find the schedule,
        //  call modifySchedule in ScheduleManager, write change in database,
        //  close the file
    }

    public static void deleteSDWrite(String scheduleID, String end) {
        // Todo open the Schedules file for this userID,
        //  read the database to find the schedule and delete the schedule in database,
        //  close the file
    }

    public static ArrayList readSD(String scheduleID, String end) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try {

            allUserSchedules = jdbcsQlite.getAllUserEventTasksByUserName(currUser.getName());

            int todoNum;
            if (allUserToDoTasks == null) {
                todoNum = 0;
            } else {
                todoNum = allUserToDoTasks.size();
            }

            int scheduleNum;
            if (allUserSchedules == null) {
                scheduleNum = 0;
            } else {
                scheduleNum = allUserSchedules.size();
            }

            int importantNum;
            if (allUserImportantTasks == null) {
                importantNum = 0;
            } else {
                importantNum = allUserImportantTasks.size();
            }

            todoNumLabel.setText(Integer.toString(todoNum));

            scheduledNumLabel.setText(Integer.toString(scheduleNum));

            importantNumLabel.setText(Integer.toString(importantNum));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }
    }

    //below are all for todolist
    /**
     * @param userID todolist will be added inside the user's ToDoListManager
     * @param listName ToDoList's name
     */
    public static void newTDWrite(String userID, String listName) {
        // Todo open the ToDoLists file inside of the userID,
        //  create a new ToDoList ID and call addList in ToDoListManage to create ToDoList
        //  and write the info in the database,
        //  close the file
    }

    public static void modifyTDWrite(String userID, String listID, String newListName) {
        // Todo open the ToDoLists file for this userID,
        //  read the database to find the ToDoList,
        //  call modifyListName in ToDoListManager, write change in database,
        //  close the file
    }

    public static void deleteTDWrite(String scheduleID, String end) {
        // Todo open the ToDoLists file for this userID,
        //  read the database to find the ToDoList and delete the ToDoList in database
    }

    public static void newTaskWrite(String userID, String listID, String task) {
        // Todo open the file inside the todolist,
        //  create a new task, write in database, close the file
    }

    public static void modifyTaskWrite(String userID, String listID, String taskID, String task) {
        // Todo open the file inside the todolist,
        //  modify the task, write in database, close the file
    }

    public static void deleteTaskWrite(String userID, String listID, String taskID) {
        // Todo open the file inside the todolist,
        //  delete the task, write in database, close the file
    }

    public static void completeTask(String userID, String listID, String taskID) {
        // Todo
    }


}
