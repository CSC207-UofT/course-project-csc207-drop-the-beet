package com.planner.Controller;

import com.Memento.Memento;
import com.planner.UseCases.ToDoListManager;
import com.planner.UseCases.UserManager;
import com.database.JDBCSQlite;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ToDoListsController {
    public static void newToDoList(String userID, String listName) {
        //call infoReadWriter to open the file first,
    }

//    public static void modifyToDoList(String userID, String listID, String newListName) {
//
//    }
//
//    public static void deleteToDoList(String listID) {
//
//    }

    public static void newTask(UserManager user, String task, LocalDate deadline) {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();

        try {
            Integer taskID = jdbcsQlite.getLastToDoTaskID();
            user.getToDoLists().addTask(taskID, task, deadline);
            jdbcsQlite.createUserToDoListTaskByUserName(user.getName(), user.getToDoLists().getTask(taskID),
                    user.getToDoLists().getDeadline(taskID));
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

    private ToDoListManager state;
    /* lots of memory consumptive private data that is not necessary to define the
     * state and should thus not be saved. Hence the small memento object. */

    public void setState(ToDoListManager state) {
        System.out.println("Originator: Setting state to " + state);
        this.state = state;
    }

    public Memento save() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(state);
    }
    public void restore(Memento m) {
        state = m.getState();
        System.out.println("Originator: State after restoring from Memento: " + state);
    }
}
