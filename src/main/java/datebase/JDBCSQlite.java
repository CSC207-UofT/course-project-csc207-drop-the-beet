package datebase;
import java.sql.*;

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

    public boolean isTableExist(String table) {
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

    public void InitizalizeDB() throws SQLException {
        if (!isTableExist("ACCOUNT")){
            stmt.executeUpdate(createAccountTableSql);
        }
    }

    public void createNewUser(String userName, String userEmail, String userPassword) throws SQLException {
        ResultSet rs = stmt.executeQuery(getMaxIDSql);
        rs.next();
        int nextID = 1;
        String s = rs.getString("MAX(ACCOUNT.ID)");
        if (s == null || s.length() <= 0) {
            stmt.executeUpdate(createNewUserSql + "(" + Integer.toString(nextID) + "," + "'" + userName + "'" + "," + "'" + userEmail + "'" + "," + "'" + userPassword + "'" + ")");
        } else {
            nextID = Integer.parseInt(s) + 1;
            stmt.executeUpdate(createNewUserSql + "(" + Integer.toString(nextID) + "," + "'" + userName + "'" + "," + "'" + userEmail + "'" + "," + "'" + userPassword + "'" + ")");
        }

    }

    public static void main(String[] args) throws SQLException {
        System.out.println(System.getProperty("user.dir"));
        JDBCSQlite jdbcsqlite = new JDBCSQlite();
        jdbcsqlite.create();
        jdbcsqlite.InitizalizeDB();
        jdbcsqlite.createNewUser("UserName", "Test@Email.com", "PAss");
        jdbcsqlite.close();
    }
}
