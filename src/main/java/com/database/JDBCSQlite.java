package com.database;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class JDBCSQlite {
    /**
     * Connect with JDBC
     * Creating tables and get values from the tables
     * operating SQL statements by SQLite
     */

    Connection dbConnection;
    Statement stmt;

    String createAccountTableSql = "CREATE TABLE ACCOUNT" +
            "(ID INT PRIMARY KEY NOT NULL," +
            " USERNAME TEXT NOT NULL, " +
            " EMAIL    TEXT NOT NULL," +
            " PASSWORD TEXT NOT NULL)";


//    String getUserEmailByIDSql = "SELECT EMAIL FROM ACCOUNT WHERE ACCOUNT.ID = ";

    /**
     * Connect to the database.
     */
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

    /**
     * Initialize the database, with the schemas created, including the
     * necessary attributes for the four tables.
     */
    public void InitializeDB() throws SQLException {

        if (!isTableExist("ACCOUNT")){
            stmt.executeUpdate(createAccountTableSql);
        }

        if (!isTableExist("TODOLIST")) {
            stmt.executeUpdate("CREATE TABLE TODOLIST" +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " USERID INT NOT NULL," +
                    " USERNAME TEXT NOT NULL, " +
                    " TASK     TEXT NOT NULL," +
                    " END      TEXT NOT NULL)");
        }

        if (!isTableExist("IMPORTANT")) {
            stmt.executeUpdate("CREATE TABLE IMPORTANT" +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " USERID INT NOT NULL," +
                    " USERNAME TEXT NOT NULL, " +
                    " TASK     TEXT NOT NULL," +
                    " START    TEXT NOT NULL," +
                    " END      TEXT NOT NULL)");
        }

        if (!isTableExist("EVENT")) {
            stmt.executeUpdate("CREATE TABLE EVENT" +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " USERID INT NOT NULL," +
                    " USERNAME TEXT NOT NULL, " +
                    " TASK     TEXT NOT NULL," +
                    " START    TEXT NOT NULL," +
                    " END      TEXT NOT NULL)");
        }

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
     * Check if the tables are already stored in the database.
     *
     * @param table The database table saved in the system
     */
    private boolean isTableExist(String table) {

        boolean flag = false;
        try {
            DatabaseMetaData meta = dbConnection.getMetaData();
            ResultSet rs = meta.getTables(null, null, table, new String[] {"TABLE"});
            flag = rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return flag;
    }

    /**
     * To create the event task including the task, the starting time and the end time by the Username.
     *
     * @param userName the user's name
     * @param task the user's task set to complete
     * @param start the starting time of the plan for some task(s)
     * @param end the deadline of the plan for some task(s)
     */
    public boolean createEventTaskByUserName(String userName, String task, LocalDate start ,LocalDate end) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT MAX(EVENT.ID) FROM EVENT");
        int nextID = 1;
        if (rs.next()) {
            nextID = rs.getInt(1) + 1;
        }
        int userID = getUserIDByUserName(userName);
        if (userID != -1) {
            stmt.executeUpdate("INSERT INTO EVENT VALUES (" + nextID + ","  + userID  + "," + "'" + userName + "'" + "," + "'" + task + "'" + "," + "'" + start.toString()  + "'" + "," + "'" + end.toString() + "'" + ")");
            return true;
        }
        return false;
    }

    /**
     * To delete the event of the user by the TaskID.
     *
     * @param taskID the task's ID for some task(s)
     */
    public boolean deleteUserEventListByTaskID(int taskID) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE ID = " + taskID);
        if (rs.next()) {
            stmt.executeUpdate("DELETE FROM EVENT WHERE ID = " + taskID);
            return true;
        }
        return false;
    }


    /**
     * To obtain every event tasks by the unique UserName.
     *
     * @param userName the username of the user
     */
    public List<List<String>> getAllUserEventTasksByUserName(String userName) throws SQLException {

        List<List<String>> res = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE USERNAME = " + "'" + userName + "'");
        return fetchAllEventsToArrayList(res, rs);
    }


    /**
     * To obtain every event of an user.
     */
    @Nullable
    private List<List<String>> fetchAllEventsToArrayList(List<List<String>> res, ResultSet rs) throws SQLException {

        while (rs.next()) {
            List<String> tmp = new ArrayList<>();
            for (int i = 1; i < 7; ++i){
                tmp.add(rs.getString(i));
            }
            res.add(tmp);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    /**
     * To create important task including the starting time and end time by the UserName.
     *
     * @param userName the username for the user
     * @param task the user's task set to complete
     * @param start the starting time of the plan for some task(s)
     * @param end the deadline of the plan for some task(s)
     */
    public boolean createImportantTaskByUserName(String userName, String task, LocalDate start ,LocalDate end) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT MAX(IMPORTANT.ID) FROM IMPORTANT");
        int nextID = 1;
        if (rs.next()) {
            nextID = rs.getInt(1) + 1;
        }
        int userID = getUserIDByUserName(userName);
        if (userID != -1) {
            stmt.executeUpdate("INSERT INTO IMPORTANT VALUES (" + nextID + ","  + userID  + "," + "'" + userName + "'" + "," + "'" + task + "'" + "," + "'" + start.toString()  + "'" + "," + "'" + end.toString() + "'" + ")");
            return true;
        }
        return false;
    }

    /**
     * To delete the important task of an user by the unique task ID.
     *
     * @param TaskID the task's ID for some task(s)
     */
    public boolean deleteUserImportantListByTaskID(int TaskID) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("DELETE FROM IMPORTANT WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    /**
     * To obtain every important task of the user by the unique UserName.
     *
     * @param userName the username of the user
     */
    public List<List<String>> getAllUserImportantTasksByUserName(String userName) throws SQLException {

        List<List<String>> res = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE USERNAME = " + "'" + userName + "'");
        return fetchAllEventsToArrayList(res, rs);
    }
}
