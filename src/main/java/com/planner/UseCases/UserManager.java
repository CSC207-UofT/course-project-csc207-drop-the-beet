package com.planner.UseCases;


import com.planner.Entities.User;

public class UserManager {
    User user;
    ScheduleManager schedules;
    ToDoListManager toDoLists;
    ScheduleManager important;

    public UserManager(String name, String email, String password) {
        this.user = new User(name,email,password);
        this.schedules = new ScheduleManager();
        this.toDoLists = new ToDoListManager();
        this.important = new ScheduleManager();
    }

    public String getName() {
        return this.user.getName();}

    public String getEmail() {
        return this.user.getEmail();}

    public String getPassword() {
        return this.user.getPassword();}

    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    public void setSchedules(ScheduleManager schedules) {
        this.schedules = schedules;
    }

    public void setToDoLists(ToDoListManager toDoLists) {
        this.toDoLists = toDoLists;
    }

    public void setImportant(ScheduleManager important) {this.important = important;}

    public ScheduleManager getSchedules() {
        return this.schedules;
    }

    public ToDoListManager getToDoLists() {
        return this.toDoLists;
    }

    public ScheduleManager getImportant() {return this.important;}
}
