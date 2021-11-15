package com.database;
import javafx.concurrent.Task;
import org.jetbrains.annotations.Nullable;
import org.xml.sax.Locator;

import javax.xml.transform.Result;
import java.nio.file.FileSystemAlreadyExistsException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

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

    String createNewUserSql = "INSERT INTO ACCOUNT VALUES ";

    String getMaxIDSql = "SELECT MAX(ACCOUNT.ID) FROM ACCOUNT";

    String getUserPasswordSql = "SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

    String getUserPasswordByIDSql = "SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT.ID = ";

    String getUserNameSql = "SELECT USERNAME FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

    String getUserIDSql = "SELECT ID FROM ACCOUNT WHERE ACCOUNT.ID = ";

    String getUserEmailSql = "SELECT EMAIL FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

    String getUserEmailByIDSql = "SELECT EMAIL FROM ACCOUNT WHERE ACCOUNT.ID = ";

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
     * Create a new user to the planner with the user's basic info
     *
     * @param userName the user's username
     * @param userEmail the user's email
     * @param userPassword the user's password
     */
    public boolean createNewUser(String userName, String userEmail, String userPassword) throws SQLException {

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
            return true;
        }
        return false;
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
     * check if the userID of the user exists
     *
     * @param ID the User Account table's primary ID
     */
    public boolean isUserIDExist(int ID) throws SQLException {

        ResultSet rs = stmt.executeQuery(getUserIDSql + "'" + ID + "'");
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
     * Search the username by its unique ID
     *
     * @param ID the User Account table's primary ID
     */
    public String getUserNameByID(int ID) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT USERNAME FROM ACCOUNT WHERE ACCOUNT.ID = " + ID);
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
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
     * Get the password of the user by its unique ID.
     *
     * @param ID the User Account table's primary ID
     */
    public String getUserPasswordByID(int ID) throws SQLException {

        ResultSet rs = stmt.executeQuery(getUserPasswordByIDSql + Integer.toString(ID));
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
     * get the user's email by its unique ID.
     *
     * @param ID primary ID in the table
     */
    public String getUserEmailByID(int ID) throws SQLException {

        ResultSet rs = stmt.executeQuery(getUserEmailByIDSql + "'" + Integer.toString(ID) + "'");
        if (rs.next()) {
            return rs.getString("EMAIL");
        } else {
            return null;
        }
    }

    /**
     * Change the username of the user by its unique ID.
     *
     * @param ID primary ID in the table
     * @param newUserName a new username created for the user
     */
    public boolean changeUserNameByID (int ID, String newUserName) throws SQLException {

        if (isUserIDExist(ID)){
            stmt.executeUpdate("UPDATE ACCOUNT SET USERNAME = " + "'" + newUserName + "'" + " WHERE ID = " + Integer.toString(ID) + ";");
            return true;
        }
        return false;
    }

    /**
     * Update the username of the user bt its username.
     *
     * @param oldUserName the old username created for the user
     * @param newUserName a new username created for the user
     */
    public boolean changeUserNameByUserName(String oldUserName, String newUserName) throws SQLException {

        if (isUserNameExist(oldUserName)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET USERNAME = " + "'" + newUserName + "'" + " WHERE USERNAME = " + "'" + oldUserName + "'" + ";");
            return true;
        }
        return false;
    }

    /**
     * Change the user's email by its username and update it.
     *
     * @param userName the user's username
     * @param newEmail the new user email
     */
    public boolean changeUserEmailByUserName(String userName, String newEmail) throws SQLException {

        if (isUserNameExist(userName)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET EMAIL =" + "'" + newEmail + "'" + "WHERE USERNAME = " + "'" + userName + "'");
            return true;
        }
        return false;
    }

    /**
     * Change the user's email by its unique ID.
     *
     * @param ID primary ID in the table
     * @param newEmail the new user email
     */
    public boolean changeUserEmailByID(int ID, String newEmail) throws SQLException {

        if (isUserIDExist(ID)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET EMAIL =" + "'" + newEmail + "'" + "WHERE ID = " + Integer.toString(ID));
            return true;
        }
        return false;
    }

    /**
     * change the user's password by its username and create a new password.
     *
     * @param userName the user's username
     * @param newPassword the new user password
     */
    public boolean changeUserPasswordByUserName(String userName, String newPassword) throws SQLException {

        if (isUserNameExist(userName)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET PASSWORD =" + "'" + newPassword + "'" + "WHERE USERNAME = " + "'" + userName + "'");
            return true;
        }
        return false;
    }

    /**
     * change the user's password by its unique ID to make a new password.
     *
     * @param ID primary ID in the table
     * @param newPassword: the new user password
     */
    public boolean changeUserPasswordByID(int ID, String newPassword) throws SQLException {

        if (isUserIDExist(ID)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET PASSWORD =" + "'" + newPassword + "'" + "WHERE ID = " + ID);
            return true;
        }
        return false;
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
     * Create a UserToDoList with the tasks by the user's username with the deadline.
     *
     * @param userID the user's ID
     * @param task the user's task set to complete
     * @param end the deadline of the plan for some task(s)
     */
    public boolean createUserToDoListTaskByUserID(int userID, String task, LocalDate end) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT MAX(TODOLIST.ID) FROM TODOLIST");
        int nextID = 1;
        if (rs.next()) {
            nextID = rs.getInt(1) + 1;
        }
        String userName = getUserNameByID(userID);
        if (userName != null && userName.length() > 0) {
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
     * Update the plan by changing the UserToDoList with the tasks by the user's username with the deadline.
     *
     * @param TaskID the ID of the task to complete
     * @param task the user's task set to complete
     */
    public boolean changeUserToDoListTaskTaskByTaskID(int TaskID, String task) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE ID = " + TaskID);
        if (rs.next()) {
            System.out.println("UPDATE TODOLIST SET TASK = " + "'" + task + "'" + " WHERE ID = " + TaskID);
            stmt.executeUpdate("UPDATE TODOLIST SET TASK = " + "'" + task + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    /**
     * Change the deadline of the tasks of the ToDoList by the ID of Task.
     *
     * @param TaskID the ID of the task to complete
     * @param end the deadline of the plan for some task(s)
     */
    public boolean changeUserToDoListTaskEndByTaskID(int TaskID, LocalDate end) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE TODOLIST SET END = " + "'" + end.toString() + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
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
     * To get the user's every ToDoTask by its userID.
     *
     * @param userID the user's unique ID
     */
    public ArrayList<ArrayList<String>> getUserAllToDoTasksByUserID(int userID) throws SQLException {

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if (isUserIDExist(userID)) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE USERID = " + userID);
            fetchAllUserToDoTasks(res, rs);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    /**
     * To get the user's every ToDoTask by its username.
     *
     * @param userName the user's username
     */
    public ArrayList<ArrayList<String>> getAllUserToDoTasksByUserName(String userName) throws SQLException {

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if (isUserNameExist(userName)) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE USERNAME = " + "'" + userName + "'");
            fetchAllUserToDoTasks(res, rs);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getAllUserToDoTasksTodayByUserName(String userName) throws SQLException {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        if (isUserNameExist(userName)) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE USERNAME = " + "'" + userName + "'" + " AND" + " END = " + "'" + LocalDate.now().toString() + "'");
            fetchAllUserToDoTasks(res, rs);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

    private void fetchAllUserToDoTasks(ArrayList<ArrayList<String>> res, ResultSet rs) throws SQLException {
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
     * To create the event task including the task, the starting time and the end time by the UserID.
     *
     * @param userID the user's unique ID
     * @param task the user's task set to complete
     * @param start the starting time of the plan for some task(s)
     * @param end the deadline of the plan for some task(s)
     */
    public boolean createEventTaskByUserID(int userID, String task, LocalDate start ,LocalDate end) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT MAX(EVENT.ID) FROM EVENT");
        int nextID = 1;
        if (rs.next()) {
            nextID = rs.getInt(1) + 1;
        }
        String userName = getUserNameByID(userID);
        if (userName != null && userName.length() > 0) {
            stmt.executeUpdate("INSERT INTO EVENT VALUES (" + nextID + ","  + userID  + "," + "'" + userName + "'" + "," + "'" + task + "'" + "," + "'" + start.toString()  + "'" + "," + "'" + end.toString() + "'" + ")");
            return true;
        }
        return false;
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
     * To update the event task by the TaskID of the user.
     *
     * @param taskID the task's ID for some task(s)
     * @param task the user's task set to complete
     */
    public boolean changeUserEventTaskByTaskID(int taskID, String task) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE ID = " + taskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE EVENT SET TASK = " + "'" + task + "'" + " WHERE ID = " + taskID);
            return true;
        }
        return false;
    }

    /**
     * To update the event starting time by the TaskID of the user.
     *
     * @param taskID the task's ID for some task(s)
     * @param start the starting time of the plan for some task(s)
     */
    public boolean changeUserEventTaskStartByTaskID(int taskID, LocalDate start) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE ID = " + taskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE EVENT SET START = " + "'" + start.toString() + "'" + " WHERE ID = " + taskID);
            return true;
        }
        return false;
    }

    /**
     * To update the event ending time by the TaskID of the user.
     *
     * @param taskID the task's ID for some task(s)
     * @param end the deadline of the plan for some task(s)
     */
    public boolean changeUserEventTaskEndByTaskID(int taskID, LocalDate end) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE ID = " + taskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE EVENT SET END = " + "'" + end.toString() + "'" + " WHERE ID = " + taskID);
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
     * To obtain every event tasks by the unique UserID.
     *
     * @param userID the ID for the user
     */
    public ArrayList<ArrayList<String>> getAllUserEventTasksByUserID(int userID) throws SQLException {

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE USERID = " + userID);
        return fetchAllEventsToArrayList(res, rs);
    }

    /**
     * To obtain every event tasks by the unique UserName.
     *
     * @param userName the username of the user
     */
    public ArrayList<ArrayList<String>> getAllUserEventTasksByUserName(String userName) throws SQLException {

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE USERNAME = " + "'" + userName + "'");
        return fetchAllEventsToArrayList(res, rs);
    }

    /**
     * To obtain every important task of the user by the unique UserID.
     *
     * @param userID the unique ID for the user
     */
    public ArrayList<ArrayList<String>> getAllUserImportantTasksByUserID(int userID) throws SQLException {

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE USERID = " + userID);
        return fetchAllEventsToArrayList(res, rs);
    }

    /**
     * To obtain every important task of the user by the unique UserName.
     *
     * @param userName the username of the user
     */
    public ArrayList<ArrayList<String>> getAllUserImportantTasksByUserName(String userName) throws SQLException {

        ArrayList<ArrayList<String>> res = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE USERNAME = " + "'" + userName + "'");
        return fetchAllEventsToArrayList(res, rs);
    }

    /**
     * To obtain every event of an user.
     */
    @Nullable
    private ArrayList<ArrayList<String>> fetchAllEventsToArrayList(ArrayList<ArrayList<String>> res, ResultSet rs) throws SQLException {

        while (rs.next()) {
            ArrayList<String> tmp = new ArrayList<>();
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
     * To create important task including the starting time and end time by the UserID.
     *
     * @param userID the unique ID for the user
     * @param task the user's task set to complete
     * @param start the starting time of the plan for some task(s)
     * @param end the deadline of the plan for some task(s)
     */
    public boolean createImportantTaskByUserID(int userID, String task, LocalDate start ,LocalDate end) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT MAX(IMPORTANT.ID) FROM IMPORTANT");
        int nextID = 1;
        if (rs.next()) {
            nextID = rs.getInt(1) + 1;
        }
        String userName = getUserNameByID(userID);
        if (userName != null && userName.length() > 0) {
            stmt.executeUpdate("INSERT INTO IMPORTANT VALUES (" + nextID + ","  + userID  + "," + "'" + userName + "'" + "," + "'" + task + "'" + "," + "'" + start.toString()  + "'" + "," + "'" + end.toString() + "'" + ")");
            return true;
        }
        return false;
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
     * To change important task  by the UserID.
     *
     * @param taskID the task's ID for some task(s)
     * @param task the user's task set to complete
     */
    public boolean changeUserImportantTaskByTaskID(int taskID, String task) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE ID = " + taskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE IMPORTANT SET TASK = " + "'" + task + "'" + " WHERE ID = " + taskID);
            return true;
        }
        return false;
    }

    /**
     * To change the starting time of the important task by the unique task ID.
     *
     * @param TaskID the task's ID for some task(s)
     * @param start the starting time of the plan for some task(s)
     */
    public boolean changeUserImportantTaskStartByTaskID(int TaskID, LocalDate start) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE IMPORTANT SET START = " + "'" + start.toString() + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    /**
     * To change the end time of the important task by the unique task ID.
     *
     * @param TaskID the task's ID for some task(s)
     * @param end the deadline of the plan for some task(s)
     */
    public boolean changeUserImportantTaskEndByTaskID(int TaskID, LocalDate end) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE IMPORTANT SET END = " + "'" + end.toString() + "'" + " WHERE ID = " + TaskID);
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

    public static void main(String[] args) throws SQLException {
        System.out.println(System.getProperty("user.dir"));
        JDBCSQlite jdbcsqlite = new JDBCSQlite();
        jdbcsqlite.create();
        jdbcsqlite.InitializeDB();
        if (jdbcsqlite.createNewUser("user", "Test@Email.com", "PAss") ){
            System.out.println("Crated successfully.");
        } else {
            System.out.println("Crated failed, user name duplicate.");
        }
        System.out.println("User pw is: " + jdbcsqlite.getUserPasswordByID(111));
        System.out.println(jdbcsqlite.getUserEmail("UserName"));
        System.out.println(jdbcsqlite.getUserEmail("DNE"));
        System.out.println(jdbcsqlite.getUserEmailByID(11));
        System.out.println(jdbcsqlite.getUserEmailByID(999));
        System.out.println(jdbcsqlite.changeUserNameByID(2, "NewUserName"));
        jdbcsqlite.close();
    }
}
