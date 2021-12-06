package com.database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class DBTodoList {
    Connection dbConnection;
    Statement stmt;

    String createNewUserSql = "INSERT INTO ACCOUNT VALUES ";

    String getMaxIDSql = "SELECT MAX(ACCOUNT.ID) FROM ACCOUNT";

    String getUserPasswordSql = "SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

//    String getUserPasswordByIDSql = "SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT.ID = ";

    String getUserNameSql = "SELECT USERNAME FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

//    String getUserIDSql = "SELECT ID FROM ACCOUNT WHERE ACCOUNT.ID = ";

    String getUserEmailSql = "SELECT EMAIL FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

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

    public boolean isUserNameExist(String userName) throws SQLException {

        ResultSet rs = stmt.executeQuery(getUserNameSql + "'" + userName + "'");
        return rs.next();
    }

    /**
     * Create a UserToDoList with the tasks by the user's username with the deadline.
     *
     * @param userName the user's username
     * @param task the user's task set to complete
     * @param end the deadline of the plan for some task(s)
     */
    public boolean createUserToDoListTaskByUserName(String userName, String task, LocalDate end) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT MAX(TODOLIST.ID) FROM TODOLIST");
        int nextID = 1;
        if (rs.next()) {
            nextID = rs.getInt(1) + 1;
        }
        int userID = getUserIDByUserName(userName);
        if (userID != -1) {
            stmt.executeUpdate("INSERT INTO TODOLIST VALUES (" + nextID + "," + "'" + userID + "'" + "," + "'" + userName + "'" + "," + "'" + task + "'" + "," + "'" + end.toString() + "'" + ")");
            return true;
        }
        return false;
    }

    /**
     * get a new ID
     */
    public int getLastToDoTaskID() throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT MAX(TODOLIST.ID) FROM TODOLIST");
        int nextID = 1;
        if (rs.next()) {
            nextID = rs.getInt(1) + 1;
        }
        return nextID;
    }

    /**
     * To delete the user's ToDoList by its unique ID of the Task.
     *
     * @param TaskID the ID of the task to complete
     */
    public boolean deleteUserToDoListByTaskID(int TaskID) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("DELETE FROM TODOLIST WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }


    /**
     * To get the user's every ToDoTask by its username.
     *
     * @param userName the user's username
     */
    public List<List<String>> getAllUserToDoTasksByUserName(String userName) throws SQLException {
        List<List<String>> res = new ArrayList<>();
        if (isUserNameExist(userName)) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE USERNAME = " + "'" + userName + "'");
            fetchAllUserToDoTasks(res, rs);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    public List<List<String>> getAllUserToDoTasksTodayByUserName(String userName) throws SQLException {
        List<List<String>> res = new ArrayList<>();
        if (isUserNameExist(userName)) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE USERNAME = " + "'" + userName + "'" + " AND" + " END = " + "'" + LocalDate.now() + "'");
            fetchAllUserToDoTasks(res, rs);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }




    private void fetchAllUserToDoTasks(List<List<String>> res, ResultSet rs) throws SQLException {
        while (rs.next()) {
            ArrayList<String> tmp = new ArrayList<>();
            tmp.add(rs.getString(1));
            tmp.add(rs.getString(2));
            tmp.add(rs.getString(3));
            tmp.add(rs.getString(4));
            tmp.add(rs.getString(5));
            res.add(tmp);
        }
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

}
