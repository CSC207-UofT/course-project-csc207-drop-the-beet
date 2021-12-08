package com.database;

import java.sql.*;

public class DBUser {
    Connection dbConnection;
    Statement stmt;

    public void create() {

        try {
            Class.forName("org.sqlite.JDBC");
            dbConnection = DriverManager.getConnection("jdbc:sqlite:./src/main/resources/database/test.db");
            System.out.println("DB Connected");
            stmt = dbConnection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the connection to database.
     */
    public void close() {

        try {
            stmt.close();
            dbConnection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    String createNewUserSql = "INSERT INTO ACCOUNT VALUES ";

    String getMaxIDSql = "SELECT MAX(ACCOUNT.ID) FROM ACCOUNT";

    String getUserPasswordSql = "SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

//    String getUserPasswordByIDSql = "SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT.ID = ";

    String getUserNameSql = "SELECT USERNAME FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

//    String getUserIDSql = "SELECT ID FROM ACCOUNT WHERE ACCOUNT.ID = ";

    String getUserEmailSql = "SELECT EMAIL FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

    /**
     * Create a new user to the planner with the user's basic info
     *  @param userName the user's username
     * @param userEmail the user's email
     * @param userPassword the user's password
     */
    public void createNewUser(String userName, String userEmail, String userPassword) throws SQLException {
        if (!isUserNameExist(userName)) {
            ResultSet rs = stmt.executeQuery(getMaxIDSql);
            rs.next();
            int nextID = 1;
            String s = rs.getString("MAX(ACCOUNT.ID)");
            if (s != null && s.length() > 0) {
                nextID = Integer.parseInt(s) + 1;
            }
            stmt.executeUpdate(createNewUserSql + "(" + nextID + "," + "'" + userName + "'" +
                    "," + "'" + userEmail + "'" + "," + "'" + userPassword + "'" + ")");
        }
    }

    /**
     * check if a username exists in the system
     *
     * @param userName the user's username
     */
    public boolean isUserNameExist(String userName) throws SQLException {

        ResultSet rs = stmt.executeQuery(getUserNameSql + "'" + userName + "'");
        return rs.next();
    }

    /**
     * Search the user ID by its related UserName
     *
     * @param userName the user's username
     */
    public int getUserIDByUserName(String userName) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT ID FROM ACCOUNT WHERE ACCOUNT.USERNAME = " + "'" + userName + "'");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    /**
     * Get the password of the user by its username.
     *
     * @param userName the user's username
     */
    public String getUserPassword(String userName) throws SQLException {

        ResultSet rs = stmt.executeQuery(getUserPasswordSql + "'" + userName + "'");
        if (rs.next()) {
            return rs.getString("PASSWORD");
        } else {
            return null;
        }
    }

    /**
     * get the user's email by its username.
     *
     * @param userName the user's username
     */
    public String getUserEmail(String userName) throws SQLException {

        ResultSet rs = stmt.executeQuery(getUserEmailSql + "'" + userName + "'");
        if (rs.next()) {
            return rs.getString("EMAIL");
        } else {
            return null;
        }
    }

    /**
     * Change the user's email by its username and update it.
     *  @param userName the user's username
     * @param newEmail the new user email
     */
    public void changeUserEmailByUserName(String userName, String newEmail) throws SQLException {

        if (isUserNameExist(userName)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET EMAIL =" + "'" + newEmail + "'" + "WHERE USERNAME = " + "'" + userName + "'");
        }
    }

    /**
     * change the user's password by its username and create a new password.
     *  @param userName the user's username
     * @param newPassword the new user password
     */
    public void changeUserPasswordByUserName(String userName, String newPassword) throws SQLException {

        if (isUserNameExist(userName)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET PASSWORD =" + "'" + newPassword + "'" + "WHERE USERNAME = " + "'" + userName + "'");
        }
    }

}
