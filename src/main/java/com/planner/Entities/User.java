package com.planner.Entities;

import java.io.Serializable;

/** User contains basic information of this user.
 */

public class User {
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

    public void setPassword(String password) {this.password = password; }

    public void setEmail(String newEmail) {this.email = newEmail; }

}