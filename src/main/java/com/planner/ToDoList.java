package com.planner;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.*;

/** TodoList is a list of tasks. Each task has an ID.
 * If a task is done, mark the task as complete(move that into the list of completion).
 * 这个class是一个single todolist，这个todolist里面有未完成的task和已完成的task，每个task都有自己的id
*/

public class ToDoList {
    ArrayList<HashMap<LocalDate, String>> todos; //map of todos, Map<deadline, task>
    ArrayList<HashMap<LocalDate, String>> completed; //map of completed tasks
    String listName;

    public ToDoList() {
        this.todos = new ArrayList<>();
        this.completed = new ArrayList<>();
        this.listName = "To-Do List";
    }

    public void addTask(HashMap<LocalDate, String> task) {
        this.todos.add(task);
    }

    public void removeTask(HashMap<LocalDate, String> task) {
        this.todos.remove(task);
    }

    public void modifyTask(HashMap<LocalDate, String> oldTask, HashMap<LocalDate, String> newTask) {
        this.todos.remove(oldTask);
        this.todos.add(newTask);
    }

    public void modifyName(String newListName) {
        this.listName = newListName;
    }

    public void completion(HashMap<LocalDate, String> task) {
        this.completed.add(task);
        this.todos.remove(task);
    }
}
