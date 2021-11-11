package com.planner;

import planner.Schedule;
import planner.ScheduleManager;
import planner.ToDoList;
import planner.ToDoListManager;

import java.io.Serializable;

/** User contains basic information of this user.
 */

public class User implements Serializable {
    private String name;
    private String email;
    private String password;

    /**
     *
     * Create a User.
     * @param name: the user's name
     * @param email: the user's email
     * @param password: the user's password
     */
    public User (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setName(String name) {this.name = name; }

    public void setPassword(String password) {this.password = password; }

    public void setEmail (String Email) {this.email = email; }


    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

}