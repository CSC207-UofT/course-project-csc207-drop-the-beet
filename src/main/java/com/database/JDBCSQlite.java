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

    String createNewUserSql = "INSERT INTO ACCOUNT VALUES ";

    String getMaxIDSql = "SELECT MAX(ACCOUNT.ID) FROM ACCOUNT";

    String getUserPasswordSql = "SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

//    String getUserPasswordByIDSql = "SELECT PASSWORD FROM ACCOUNT WHERE ACCOUNT.ID = ";

    String getUserNameSql = "SELECT USERNAME FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

//    String getUserIDSql = "SELECT ID FROM ACCOUNT WHERE ACCOUNT.ID = ";

    String getUserEmailSql = "SELECT EMAIL FROM ACCOUNT WHERE ACCOUNT.USERNAME = ";

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

//    /**
//     * Change the user's email by its username and update it.
//     *
//     * @param userName the user's username
//     * @param newEmail the new user email
//     */
//    public boolean changeUserEmailByUserName(String userName, String newEmail) throws SQLException {
//
//        if (isUserNameExist(userName)) {
//            stmt.executeUpdate("UPDATE ACCOUNT SET EMAIL =" + "'" + newEmail + "'" + "WHERE USERNAME = " + "'" + userName + "'");
//            return true;
//        }
//        return false;
//    }

//    /**
//     * change the user's password by its username and create a new password.
//     *
//     * @param userName the user's username
//     * @param newPassword the new user password
//     */
//    public boolean changeUserPasswordByUserName(String userName, String newPassword) throws SQLException {
//
//        if (isUserNameExist(userName)) {
//            stmt.executeUpdate("UPDATE ACCOUNT SET PASSWORD =" + "'" + newPassword + "'" + "WHERE USERNAME = " + "'" + userName + "'");
//            return true;
//        }
//        return false;
//    }

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
     * To obtain every important task of the user by the unique UserName.
     *
     * @param userName the username of the user
     */
    public List<List<String>> getAllUserImportantTasksByUserName(String userName) throws SQLException {

        List<List<String>> res = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE USERNAME = " + "'" + userName + "'");
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
     *
     * @param suchMap a Map with key as a string, value as a list of a Map with a string key
     *                and a list as values.
     * @throws SQLException Sql Exception.
     */

    public void addMap(@NotNull Map<String, List<Map<String, List<String>>>> suchMap) throws SQLException {
        for (String i: suchMap.keySet()) {
            Collection<List<String>> scheduleSet = suchMap.get(i).get(0).values();
            Collection<List<String>> todoListSet = suchMap.get(i).get(1).values();
            Collection<List<String>> importantSet = suchMap.get(i).get(2).values();
            for (List<String> scheduleLst: scheduleSet) {
                System.out.println(scheduleLst);
                ResultSet rs = stmt.executeQuery("SELECT MAX(EVENT.ID) FROM EVENT;");
                int nextID = 1;
                if (rs.next()) {
                    nextID = rs.getInt(1) + 1;
                }
                int userID = getUserIDByUserName(i);
                if (userID != -1) {
                    stmt.executeUpdate("INSERT INTO EVENT VALUES (" + nextID + ","  + userID  + "," + "'" + i + "'" + "," + "'" + scheduleLst.get(0) + "'" + "," + "'" + scheduleLst.get(1)  + "'" + "," + "'" + scheduleLst.get(2) + "'" + ")");
                }
            }

            for (List<String> todoLst: todoListSet) {
                ResultSet rs = stmt.executeQuery("SELECT MAX(TODOLIST.ID) FROM TODOLIST");
                int nextID = 1;
                if (rs.next()) {
                    nextID = rs.getInt(1) + 1;
                }
                int userID = getUserIDByUserName(i);
                if (userID != -1) {
                    stmt.executeUpdate("INSERT INTO TODOLIST VALUES (" + nextID + "," + "'" + userID + "'" + "," + "'" + i + "'" + "," + "'" + todoLst.get(0) + "'" + "," + "'" + todoLst.get(1) + "'" + ")");
                }
            }

            for (List<String> importantLst: importantSet) {
                ResultSet rs = stmt.executeQuery("SELECT MAX(IMPORTANT.ID) FROM IMPORTANT;");
                int nextID = 1;
                if (rs.next()) {
                    nextID = rs.getInt(1) + 1;
                }
                int userID = getUserIDByUserName(i);
                if (userID != -1) {
                    stmt.executeUpdate("INSERT INTO IMPORTANT VALUES (" + nextID + ","  + userID  + "," + "'" + i + "'" + "," + "'" + importantLst.get(0) + "'" + "," + "'" + importantLst.get(1)  + "'" + "," + "'" + importantLst.get(2) + "'" + ")");
                }
            }

        }
    }

    public Map<String, List<Map<String, List<String>>>> getMap(String userName) throws SQLException {
        List<List<String>> allUserSchedule = getAllUserEventTasksByUserName(userName);
        List<List<String>> allUserTodoList = getAllUserToDoTasksByUserName(userName);
        List<List<String>> allUserImportant = getAllUserImportantTasksByUserName(userName);
        Map<String, List<Map<String, List<String>>>> res = new HashMap<>();
        List<Map<String, List<String>>> allLst = new ArrayList<>();
        Map<String, List<String>> scheduleMap = new HashMap<>();
        Map<String, List<String>> todoMap = new HashMap<>();
        Map<String, List<String>> importantMap = new HashMap<>();
        getAllMap(allUserSchedule, scheduleMap);
        getAllMap(allUserImportant, importantMap);

        for (List<String> n: allUserTodoList) {
            List<String> events = new ArrayList<>();
            events.add(n.get(2));
            events.add(n.get(3));
            events.add(n.get(4));
            todoMap.put(n.get(0), events);
        }

        allLst.add(scheduleMap);
        allLst.add(todoMap);
        allLst.add(importantMap);
        res.put(userName, allLst);
        return res;
    }

    private void getAllMap(List<List<String>> allUserTodoList, Map<String, List<String>> todoMap) {
        for (List<String> m: allUserTodoList) {
            List<String> events = new ArrayList<String>();
            events.add(m.get(2));
            events.add(m.get(3));
            events.add(m.get(4));
            events.add(m.get(5));
            todoMap.put(m.get(0), events);

        }
    }

//    public static void main(String[] args) throws SQLException {
//        System.out.println(System.getProperty("user.dir"));
//        JDBCSQlite jdbcsqlite = new JDBCSQlite();
//        jdbcsqlite.create();
//        jdbcsqlite.InitializeDB();
//        if (jdbcsqlite.createNewUser("user", "Test@Email.com", "PAss") ){
//            System.out.println("Crated successfully.");
//        } else {
//            System.out.println("Crated failed, user name duplicate.");
//        }
//        System.out.println(jdbcsqlite.getUserEmail("UserName"));
//        System.out.println(jdbcsqlite.getUserEmail("DNE"));
//        List<String> tmp11 = new ArrayList<>();
//        tmp11.add("schedule");
//        tmp11.add("2020-01-01");
//        tmp11.add("2020-01-01");
//        Map<String, List<String>> tmp1 = new HashMap<>();
//        tmp1.put("1", tmp11);
//        List<String> tmp22 = new ArrayList<>();
//        tmp22.add("todo");
//        tmp22.add("2020-01-01");
//        tmp22.add("2020-01-01");
//        Map<String, List<String>> tmp2 = new HashMap<>();
//        tmp2.put("1", tmp22);
//        List<String> tmp33 = new ArrayList<>();
//        tmp33.add("important");
//        tmp33.add("2020-01-01");
//        tmp33.add("2020-01-01");
//        Map<String, List<String>> tmp3 = new HashMap<>();
//        tmp3.put("1", tmp33);
//        List<Map<String, List<String>>> tmp4 = new ArrayList<>();
//        tmp4.add(tmp1);
//        tmp4.add(tmp2);
//        tmp4.add(tmp3);
//        Map<String, List<Map<String, List<String>>>> testMap = new HashMap<>();
//        testMap.put("user", tmp4);
//        jdbcsqlite.addMap(testMap);
//        System.out.println(jdbcsqlite.getMap("user"));
//        jdbcsqlite.close();
//    }
}
