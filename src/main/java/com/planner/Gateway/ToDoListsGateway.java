package com.planner.Gateway;

import com.Memento.Memento;
import com.database.DBTodoList;
import com.planner.UseCases.ToDoListManager;
import com.planner.UseCases.UserManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ToDoListsGateway {

    public static ToDoListManager getAllToDoLists(String username) {
        DBTodoList jdbcsQlite = new DBTodoList();
        jdbcsQlite.create();

        try{
            List<List<String>> toDos = jdbcsQlite.getAllUserToDoTasksByUserName(username);
            ToDoListManager toDoListManager = new ToDoListManager();
            if (toDos == null) {
                toDos = new ArrayList<>();
            }
            toDoListManager.setToDos(toDos);
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
            if (toDos != null) {
                for (List<String> toDo : toDos) {
                    int id = Integer.parseInt(toDo.get(0));
                    jdbcsQlite.deleteUserToDoListByTaskID(id);
                }
            }
            List<List<String>> toDoWrite = user.getToDoLists().getToDoListLists();
            for (List<String> toDo : toDoWrite) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate deadline = LocalDate.parse(toDo.get(1), formatter);
                jdbcsQlite.createUserToDoListTaskByUserName(user.getName(), toDo.get(0), deadline);
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
