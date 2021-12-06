package com.planner.Gateway;

import com.Memento.Memento;
import com.database.DBTodoList;
import com.planner.Entities.ToDoList;
import com.planner.UseCases.ToDoListManager;
import com.planner.UseCases.UserManager;
import com.database.JDBCSQlite;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.database.DBUser;


public class ToDoListsGateway {

    public static ToDoListManager getAllToDoLists(String username) {
        DBTodoList jdbcsQlite = new DBTodoList();
        jdbcsQlite.create();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            List<List<String>> toDos = jdbcsQlite.getAllUserToDoTasksByUserName(username);
            ToDoListManager toDoListManager = new ToDoListManager();
            for (List<String> toDo : toDos) {
                String task = toDo.get(3);
                LocalDate deadline = LocalDate.parse(toDo.get(4), formatter);
                toDoListManager.addTask(task, deadline);
            }
            return toDoListManager;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeAllToDoList(UserManager user) {
        DBTodoList jdbcsQlite = new DBTodoList();
        jdbcsQlite.create();
        try {
            List<List<String>> toDos = jdbcsQlite.getAllUserToDoTasksByUserName(user.getName());
            for (List<String> toDo : toDos) {
                int id = Integer.parseInt(toDo.get(0));
                jdbcsQlite.deleteUserToDoListByTaskID(id);
            }
            List<ToDoList> toDoWrite = user.getToDoLists().getToDos();
            for (ToDoList toDo : toDoWrite) {
                jdbcsQlite.createUserToDoListTaskByUserName(user.getName(), toDo.getTask(), toDo.getDeadline());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }

    private ToDoListManager state;
    /* lots of memory consumptive private data that is not necessary to define the
     * state and should thus not be saved. Hence the small memento object. */

    public void setState(ToDoListManager state) {
        this.state = state;
    }

    public Memento save() {
        return new Memento(state);
    }

    public void restore(Memento m) {
        state = m.getState();
    }
}
