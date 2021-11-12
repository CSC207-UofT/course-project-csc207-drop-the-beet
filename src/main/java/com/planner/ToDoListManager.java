package com.planner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/** ToDoListManager is a map of all lists.
 * Each list has an ID.
 * 这个class是所有todolist的map，它的key是每个todolist的id，value是ToDoList
 * 这个class可以对所有有关todolist的内容进行操作
 */

public class ToDoListManager{
    Map<String, ToDoList> toDoLists;


    public ToDoListManager() {
        this.toDoLists = new HashMap<String, ToDoList>();

    }

    public void addList(String listName) {
        ToDoList toDoList = new ToDoList();
        this.toDoLists.put(listName, toDoList);
    }

    public void removeList(String listID) {
        this.toDoLists.remove(listID);
    }

    public void modifyListName(String listID, String newListName) {
        this.toDoLists.get(listID).modifyName(newListName);}

    public void addTaskInList(HashMap<LocalDate, String> task) {
        this.toDoLists.get("To-Do List").addTask(task);
    }

//    public void modifyTask(String listID, String taskID, String newTask) {
//        this.toDoLists.get(listID).modifyTask(taskID, newTask);
//    }
//
//    public void removeTask(String listID, String taskID) {
//        this.toDoLists.get(listID).removeTask(taskID);
//    }
//
//    public void completion(String listID, String taskID) {
//        this.toDoLists.get(listID).completion(taskID);
//    }
}
