package com.planner.UseCases;


import com.planner.Entities.User;

public class UserManager {
    User user;
    ScheduleManager schedules;
    ToDoListManager toDoLists;

    public UserManager(String name, String email, String password) {
        this.user = new User(name,email,password);
        this.schedules = new ScheduleManager();
        this.toDoLists = new ToDoListManager();
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

    public ScheduleManager getSchedules() {
        return this.schedules;
    }

    public ToDoListManager getToDoLists() {
        return this.toDoLists;
    }
}
