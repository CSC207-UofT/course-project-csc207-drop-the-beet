package com.planner.Entities;

import java.time.LocalDate;

/** TodoList is a list of tasks. Each task has an ID.
 * If a task is done, mark the task as complete(move that into the list of completion).
 * 这个class是一个single todolist，这个todolist里面有未完成的task和已完成的task，每个task都有自己的id
*/

public class ToDoList {
    String task;
    LocalDate deadline;

    /**
     * constructor of ToDoList
     * @param task task content
     * @param deadline deadline of this task
     */
    public ToDoList(String task, LocalDate deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    /**
     *
     * @return the task content
     */
    public String getTask() {
        return this.task;
    }

    /**
     *
     * @return the deadline
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {return this.task;}
}
