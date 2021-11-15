package com.planner.Entities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.*;

/** TodoList is a list of tasks. Each task has an ID.
 * If a task is done, mark the task as complete(move that into the list of completion).
 * 这个class是一个single todolist，这个todolist里面有未完成的task和已完成的task，每个task都有自己的id
*/

public class ToDoList {
    String task;
    LocalDate deadline;

    public ToDoList(String task, LocalDate deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    public String getTask() {
        return this.task;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void modifyTask(String task) {
        this.task = task;
    }

    public void modifyDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
