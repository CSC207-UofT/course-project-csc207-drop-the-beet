package com.planner.Gateway;

import com.database.DBTodoList;
import com.database.DBUser;
import com.database.JDBCSQlite;
import com.planner.UseCases.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserGatewayTest {
    UserManager user;

    /**
     * "abby", "abby@mail.com", "1111" has been written in database
     */
    @BeforeEach
    public void setUp() {
        user = new UserManager("abby", "abby@mail.com", "1111");
        user.setEmail("ybba@mail.com");
        user.getToDoLists().addTask("homework", LocalDate.parse("2021-12-10"));
        user.getSchedules().addSchedule(LocalDate.parse("2021-12-20"), LocalDate.parse("2021-12-26"), "vacation");
        user.getImportant().addSchedule(LocalDate.parse("2021-12-20"), LocalDate.parse("2021-12-20"), "birthday");
    }

    @Test
    public void TestWriteAllUserInfo() throws SQLException {
        UserGateway.writeAllUserInfo(user);
        DBUser dbUser = new DBUser();
        dbUser.create();
        DBTodoList dbTodoList = new DBTodoList();
        dbTodoList.create();
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try {
            assertEquals("ybba@mail.com", dbUser.getUserEmail("abby"));

            List<List<String>> toDosLists = dbTodoList.getAllUserToDoTasksByUserName("abby");
            assertEquals("abby", toDosLists.get(0).get(2));
            assertEquals("homework", toDosLists.get(0).get(3));
            assertEquals("2021-12-10", toDosLists.get(0).get(4));

            List<List<String>> schedules = jdbcsQlite.getAllUserEventTasksByUserName("abby");
            assertEquals("abby", schedules.get(0).get(2));
            assertEquals("vacation", schedules.get(0).get(3));
            assertEquals("2021-12-20", schedules.get(0).get(4));
            assertEquals("2021-12-26", schedules.get(0).get(5));

            List<List<String>> importants = jdbcsQlite.getAllUserImportantTasksByUserName("abby");
            assertEquals("abby", importants.get(0).get(2));
            assertEquals("birthday", importants.get(0).get(3));
            assertEquals("2021-12-20", importants.get(0).get(4));
            assertEquals("2021-12-20", importants.get(0).get(5));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        dbUser.close();
    }

    @Test
    public void TestLoadAllUserInfo() throws SQLException {
        UserManager user = UserGateway.loadAllUserInfo("abby");
        assert user != null;
        assertEquals("abby", user.getName());
        assertEquals("ybba@mail.com", user.getEmail());
        assertEquals("1111", user.getPassword());
        assertEquals("homework", user.getToDoLists().getToDos().get(0).getTask());
        assertEquals("2021-12-10", user.getToDoLists().getToDos().get(0).getDeadline().toString());
        assertEquals("vacation", user.getSchedules().getScheduleLists().get(0).get(0));
        assertEquals("2021-12-20", user.getSchedules().getScheduleLists().get(0).get(1));
        assertEquals("2021-12-26", user.getSchedules().getScheduleLists().get(0).get(2));
        assertEquals("birthday", user.getImportant().getScheduleLists().get(0).get(0));
        assertEquals("2021-12-20", user.getImportant().getScheduleLists().get(0).get(1));

    }

}