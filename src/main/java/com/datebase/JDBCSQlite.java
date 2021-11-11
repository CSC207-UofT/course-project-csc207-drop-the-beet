package com.datebase;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class JDBCSQlite {
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

    public void close() {
        try {
            stmt.close();
            dbConnection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void InitializeDB() throws SQLException {
        if (isTableExist("ACCOUNT")){
            stmt.executeUpdate(createAccountTableSql);
        }

        if (isTableExist("TODOLIST")) {
            stmt.executeUpdate("CREATE TABLE TODOLIST" +
                                   "(ID INT PRIMARY KEY NOT NULL," +
                                   " USERID INT NOT NULL," +
                                   " USERNAME TEXT NOT NULL, " +
                                   " TASK     TEXT NOT NULL," +
                                   " END      TEXT NOT NULL)");
        }

        if (isTableExist("IMPORTANT")) {
            stmt.executeUpdate("CREATE TABLE IMPORTANT" +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " USERID INT NOT NULL," +
                    " USERNAME TEXT NOT NULL, " +
                    " TASK     TEXT NOT NULL," +
                    " START    TEXT NOT NULL," +
                    " END      TEXT NOT NULL)");
        }

        if (isTableExist("EVENT")) {
            stmt.executeUpdate("CREATE TABLE EVENT" +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    " USERID INT NOT NULL," +
                    " USERNAME TEXT NOT NULL, " +
                    " TASK     TEXT NOT NULL," +
                    " START    TEXT NOT NULL," +
                    " END      TEXT NOT NULL)");
        }
    }

    public boolean isTableExist(String table) {
        boolean flag = false;
        try {
            DatabaseMetaData meta = dbConnection.getMetaData();
            ResultSet rs = meta.getTables(null, null, table, new String[] {"TABLE"});
            flag = rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return !flag;
    }

    public boolean createNewUser(String userName, String userEmail, String userPassword) throws SQLException {
        if (!isUserNameExist(userName)) {
            ResultSet rs = stmt.executeQuery(getMaxIDSql);
            rs.next();
            int nextID = 1;
            String s = rs.getString("MAX(ACCOUNT.ID)");
            if (s != null && s.length() > 0) {
                nextID = Integer.parseInt(s) + 1;
            }
            stmt.executeUpdate(createNewUserSql + "(" + Integer.toString(nextID) + "," + "'" + userName + "'" + "," + "'" + userEmail + "'" + "," + "'" + userPassword + "'" + ")");
            return true;
        }
        return false;
    }

    public boolean isUserNameExist(String userName) throws SQLException {
        ResultSet rs = stmt.executeQuery(getUserNameSql + "'" + userName + "'");
        return rs.next();
    }

    public boolean isUserIDExist(int ID) throws SQLException {
        ResultSet rs = stmt.executeQuery(getUserIDSql + "'" + Integer.toString(ID) + "'");
        return rs.next();
    }

    public int getUserIDByUserName(String userName) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT ID FROM ACCOUNT WHERE ACCOUNT.USERNAME = " + "'" + userName + "'");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public String getUserNameByID(int ID) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT USERNAME FROM ACCOUNT WHERE ACCOUNT.ID = " + Integer.toString(ID));
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public String getUserPassword(String userName) throws SQLException {
        ResultSet rs = stmt.executeQuery(getUserPasswordSql + "'" + userName + "'");
        if (rs.next()) {
            String res = rs.getString("PASSWORD");
            return res;
        } else {
            return null;
        }
    }

    public String getUserPasswordByID(int ID) throws SQLException {
        ResultSet rs = stmt.executeQuery(getUserPasswordByIDSql + Integer.toString(ID));
        if (rs.next()) {
            String res = rs.getString("PASSWORD");
            return res;
        } else {
            return null;
        }
    }

    public String getUserEmail(String userName) throws SQLException {
        ResultSet rs = stmt.executeQuery(getUserEmailSql + "'" + userName + "'");
        if (rs.next()) {
            String res = rs.getString("EMAIL");
            return res;
        } else {
            return null;
        }
    }

    public String getUserEmailByID(int ID) throws SQLException {
        ResultSet rs = stmt.executeQuery(getUserEmailByIDSql + "'" + Integer.toString(ID) + "'");
        if (rs.next()) {
            String res = rs.getString("EMAIL");
            return res;
        } else {
            return null;
        }
    }

    public boolean changeUserNameByID (int ID, String newUserName) throws SQLException {
        if (isUserIDExist(ID)){
            stmt.executeUpdate("UPDATE ACCOUNT SET USERNAME = " + "'" + newUserName + "'" + " WHERE ID = " + Integer.toString(ID) + ";");
            return true;
        }
        return false;
    }

    public boolean changeUserNameByUserName(String oldUserName, String newUserName) throws SQLException {
        if (isUserNameExist(oldUserName)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET USERNAME = " + "'" + newUserName + "'" + " WHERE USERNAME = " + "'" + oldUserName + "'" + ";");
            return true;
        }
        return false;
    }

    public boolean changeUserEmailByUserName(String userName, String newEmail) throws SQLException {
        if (isUserNameExist(userName)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET EMAIL =" + "'" + newEmail + "'" + "WHERE USERNAME = " + "'" + userName + "'");
            return true;
        }
        return false;
    }

    public boolean changeUserEmailByID(int ID, String newEmail) throws SQLException {
        if (isUserIDExist(ID)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET EMAIL =" + "'" + newEmail + "'" + "WHERE ID = " + Integer.toString(ID));
            return true;
        }
        return false;
    }

    public boolean changeUserPasswordByUserName(String userName, String newPassword) throws SQLException {
        if (isUserNameExist(userName)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET PASSWORD =" + "'" + newPassword + "'" + "WHERE USERNAME = " + "'" + userName + "'");
            return true;
        }
        return false;
    }

    public boolean changeUserPasswordByID(int ID, String newPassword) throws SQLException {
        if (isUserIDExist(ID)) {
            stmt.executeUpdate("UPDATE ACCOUNT SET PASSWORD =" + "'" + newPassword + "'" + "WHERE ID = " + ID);
            return true;
        }
        return false;
    }

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

    public boolean changeUserToDoListTaskTaskByTaskID(int TaskID, String task) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE ID = " + TaskID);
        if (rs.next()) {
            System.out.println("UPDATE TODOLIST SET TASK = " + "'" + task + "'" + " WHERE ID = " + TaskID);
            stmt.executeUpdate("UPDATE TODOLIST SET TASK = " + "'" + task + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    public boolean changeUserToDoListTaskEndByTaskID(int TaskID, LocalDate end) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE TODOLIST SET END = " + "'" + end.toString() + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    public boolean deleteUserToDoListByTaskID(int TaskID) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("DELETE FROM TODOLIST WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    public ArrayList<ArrayList<String>> getAllUserToDoTasksByUserID(int UserID) throws SQLException {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if (isUserIDExist(UserID)) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM TODOLIST WHERE USERID = " + UserID);
            fetchAllUserToDoTasks(res, rs);
        }
        if (res.size() > 0) {
            return res;
        }
        return null;
    }

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
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(rs.getString(1));
            tmp.add(rs.getString(2));
            tmp.add(rs.getString(3));
            tmp.add(rs.getString(4));
            tmp.add(rs.getString(5));
            res.add(tmp);
        }
    }

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

    public boolean changeUserEventTaskByTaskID(int TaskID, String task) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE EVENT SET TASK = " + "'" + task + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    public boolean changeUserEventTaskStartByTaskID(int TaskID, LocalDate start) throws SQLException {
        create();
        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE EVENT SET START = " + "'" + start.toString() + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    public boolean changeUserEventTaskEndByTaskID(int TaskID, LocalDate end) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE EVENT SET END = " + "'" + end.toString() + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    public boolean deleteUserEventListByTaskID(int TaskID) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("DELETE FROM EVENT WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    public ArrayList<ArrayList<String>> getAllUserEventTasksByUserID(int userID) throws SQLException {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE USERID = " + userID);
        return fetchAllEventsToArrayList(res, rs);
    }

    public ArrayList<ArrayList<String>> getAllUserEventTasksByUserName(String userName) throws SQLException {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM EVENT WHERE USERNAME = " + "'" + userName + "'");
        return fetchAllEventsToArrayList(res, rs);
    }


    public ArrayList<ArrayList<String>> getAllUserImportantTasksByUserID(int userID) throws SQLException {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE USERID = " + userID);
        return fetchAllEventsToArrayList(res, rs);
    }

    public ArrayList<ArrayList<String>> getAllUserImportantTasksByUserName(String userName) throws SQLException {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE USERNAME = " + "'" + userName + "'");
        return fetchAllEventsToArrayList(res, rs);
    }

    @Nullable
    private ArrayList<ArrayList<String>> fetchAllEventsToArrayList(ArrayList<ArrayList<String>> res, ResultSet rs) throws SQLException {
        while (rs.next()) {
            ArrayList<String> tmp = new ArrayList<String>();
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

    public boolean changeUserImportantTaskByTaskID(int TaskID, String task) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE IMPORTANT SET TASK = " + "'" + task + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    public boolean changeUserImportantTaskStartByTaskID(int TaskID, LocalDate start) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE IMPORTANT SET START = " + "'" + start.toString() + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

    public boolean changeUserImportantTaskEndByTaskID(int TaskID, LocalDate end) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM IMPORTANT WHERE ID = " + TaskID);
        if (rs.next()) {
            stmt.executeUpdate("UPDATE IMPORTANT SET END = " + "'" + end.toString() + "'" + " WHERE ID = " + TaskID);
            return true;
        }
        return false;
    }

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
