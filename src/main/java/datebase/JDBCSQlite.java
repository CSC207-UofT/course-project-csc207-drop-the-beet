package datebase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSQlite {
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

    public void close() {
        try {
            stmt.close();
            dbConnection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        JDBCSQlite jdbcsqlite = new JDBCSQlite();
        jdbcsqlite.create();
        jdbcsqlite.close();
    }




}
