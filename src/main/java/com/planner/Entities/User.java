package com.planner.Entities;

/** User contains basic information of this user.
 */

public class User {
    private final String name;
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

    /**
     *
     * @return username of this user
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return email of this user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     *
     * @return password of this user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * set new password
     * @param password new password
     */
    public void setPassword(String password) {this.password = password; }

    /**
     * set new email
     * @param newEmail new email
     */
    public void setEmail(String newEmail) {this.email = newEmail; }

}