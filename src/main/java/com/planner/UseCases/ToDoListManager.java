package com.planner.UseCases;

import com.planner.Entities.Schedule;
import com.planner.Entities.ToDoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    /**
     * set the toDos from giving list
     * @param toDoListLists a list of string lists, string list format [todoID, userID, task, deadline]
     */
    public void setToDos(List<List<String>> toDoListLists) {
        List<ToDoList> toDos = new ArrayList<>();
        if (toDoListLists.size() != 0) {
            for (List<String> l : toDoListLists) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String task = l.get(3);
                LocalDate deadline = LocalDate.parse(l.get(4), formatter);
                ToDoList toDoList = new ToDoList(task, deadline);
                toDos.add(toDoList);
            }
        }
        this.toDos = toDos;
    }

    /**
     *
     * @return a list of string lists, string list format [todoID, userID, task, deadline]
     */
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

    /**
     *
     * @return size of toDoList
     */
    public int getSize() {
        return this.toDos.size();
    }

    /**
     * add a new task
     * @param task new task content
     * @param deadline new deadline
     */
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
