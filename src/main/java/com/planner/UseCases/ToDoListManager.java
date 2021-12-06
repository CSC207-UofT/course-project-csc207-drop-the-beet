package com.planner.UseCases;

import com.planner.Entities.ToDoList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** ToDoListManager is a map of all lists.
 * Each list has an ID.
 * 这个class是所有todolist的map，它的key是每个todolist的id，value是ToDoList
 * 这个class可以对所有有关todolist的内容进行操作
 */

public class ToDoListManager{
    Map<Integer, ToDoList> toDos;

    public ToDoListManager() {
        this.toDos = new HashMap<>();

    }

    public void addTask(Integer taskID, String task, LocalDate deadline) {
        ToDoList toDo = new ToDoList(task, deadline);
        this.toDos.put(taskID, toDo);
    }

    public String getTask(Integer taskID) {
        return this.toDos.get(taskID).getTask();
    }

    public LocalDate getDeadline(Integer taskID) {
        return this.toDos.get(taskID).getDeadline();
    }

    public void removeTask(int taskId) {
        this.toDos.remove(taskId);
    }

//
//    public void removeTask(String listID, String taskID) {
//        this.toDoLists.get(listID).removeTask(taskID);
//    }
//
//    public void completion(String listID, String taskID) {
//        this.toDoLists.get(listID).completion(taskID);
//    }
}
