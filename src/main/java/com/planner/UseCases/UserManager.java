package com.planner.UseCases;


import com.planner.Entities.User;

/**
 * User manager is a manager of user. It has four attributes:
 * - user: user currently logged in
 * - schedules: user's schedules
 * - todolists: user's todolists
 * - important: user's important schedules
 */
public class UserManager {
    User user;
    ScheduleManager schedules;
    ToDoListManager toDoLists;
    ScheduleManager important;

    /**
     * @param name: user's name
     * @param email: user's email
     * @param password: user's password
     */
    public UserManager(String name, String email, String password) {
        this.user = new User(name,email,password);
        this.schedules = new ScheduleManager();
        this.toDoLists = new ToDoListManager();
        this.important = new ScheduleManager();
    }

    /**
     * @return user's name
     */
    public String getName() {
        return this.user.getName();}

    /**
     * @return user's email
     */
    public String getEmail() {
        return this.user.getEmail();}

    /**
     * @return user's password
     */
    public String getPassword() {
        return this.user.getPassword();}

    /**
     * set user's email
     * @param email: new email
     */
    public void setEmail(String email) {
        this.user.setEmail(email);
    }

    /**
     * set user's password
     * @param password: new password
     */
    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    /**
     * ser user's schedules
     * @param schedules: new schedule
     */
    public void setSchedules(ScheduleManager schedules) {
        this.schedules = schedules;
    }

    /**
     * set user's todolist
     * @param toDoLists: new todolist
     */
    public void setToDoLists(ToDoListManager toDoLists) {
        this.toDoLists = toDoLists;
    }

    /**
     * set user's important
     * @param important: new important schedule
     */
    public void setImportant(ScheduleManager important) {this.important = important;}

    /**
     * @return user's schedules
     */
    public ScheduleManager getSchedules() {
        return this.schedules;
    }

    /**
     * @return user's todolist
     */
    public ToDoListManager getToDoLists() {
        return this.toDoLists;
    }

    /**
     * @return user's important schedules
     */
    public ScheduleManager getImportant() {return this.important;}
}
