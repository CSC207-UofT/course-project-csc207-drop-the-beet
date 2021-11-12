package com.planner;

import com.datebase.JDBCSQlite;
import com.planner.UserManager;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ToDoListsController {
    public static void newToDoList(String userID, String listName) {
        //call infoReadWriter to open the file first,
    }

    public static void modifyToDoList(String userID, String listID, String newListName) {

    }

    public static void deleteToDoList(String listID) {

    }

    public static void newTask(UserManager user, String task, LocalDate deadline) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();

        try {
//            HashMap<LocalDate, String> todo = new HashMap<>();
//            todo.put(deadline, task);
//            user.getToDoLists().addTaskInList(todo);
            jdbcsQlite.createUserToDoListTaskByUserName(user.getName(), task, deadline);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifyTask(String userID, String listID, String taskID, String newTask) {

    }

    public static void deleteTask(String userID, String listID, String taskID) {

    }

    public static void completeTask(String userID, String listID, String taskID) {

    }

    public static ArrayList<ArrayList<String>> loadToDo(UserManager user) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        ArrayList<ArrayList<String>> lst = null;
        try{
            lst = jdbcsQlite.getAllUserToDoTasksByUserName(user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jdbcsQlite.close();
        return lst;
    }

    public static int loadTodoBubble(UserManager currUser, JDBCSQlite jdbcsQlite) {
        int todoNum = 0;
        try {
            ArrayList<ArrayList<String>> allUserToDoTasks = jdbcsQlite.getAllUserToDoTasksByUserName(currUser.getName());

            if (allUserToDoTasks != null) {
                todoNum = allUserToDoTasks.size();
            }
            return todoNum;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return todoNum;
    }
}
