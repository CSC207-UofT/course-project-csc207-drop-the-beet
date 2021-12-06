package com.planner.UseCases;

import com.planner.Entities.Schedule;
import com.planner.Entities.ToDoList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** ToDoListManager is a map of all lists.
 * Each list has an ID.
 * 这个class是所有todolist的map，它的key是每个todolist的id，value是ToDoList
 * 这个class可以对所有有关todolist的内容进行操作
 */

public class ToDoListManager{
    List<ToDoList> toDos;

    public ToDoListManager() {
        this.toDos = new ArrayList<>();

    }

//    public List<ToDoList> getToDos() {
//        return this.toDos;
//    }

    public List<List<String>> getToDoListLists() {
        List<List<String>> toDoListLists = new ArrayList<>();
        for (ToDoList toDoList : this.toDos) {
            List<String> toDoListList = new ArrayList<>();
            toDoListList.add(toDoList.getTask());
            toDoListList.add(toDoList.getDeadline().toString());
            toDoListLists.add(toDoListList);
        }
        return toDoListLists;
    }

    public int getSize() {
        return this.toDos.size();
    }

    public void addTask(String task, LocalDate deadline) {
        ToDoList toDo = new ToDoList(task, deadline);
        this.toDos.add(toDo);
    }

//    public String getTask(Integer taskID) {
//        return this.toDos.get(taskID).getTask();
//    }
//
//    public LocalDate getDeadline(Integer taskID) {
//        return this.toDos.get(taskID).getDeadline();
//    }
//
//    public void removeTask(int taskId) {
//        this.toDos.remove(taskId);
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
