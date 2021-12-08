package com.planner.Gateway;

import com.database.DBTodoList;
import com.database.JDBCSQlite;
import com.planner.UseCases.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportantGatewayTest {
    UserManager user;

    /**
     * "abby", "abby@mail.com", "1111" has been written in database
     */
    @BeforeEach
    public void setUp() {
        user = new UserManager("abby", "abby@mail.com", "1111");
        UserGateway.writeAllUserInfo(user);
        user.getImportant().addSchedule(LocalDate.parse("2021-12-10"), LocalDate.parse("2021-12-10"), "homework");
    }


    @Test
    public void TestWriteAllImportant() {
        ImportantGateway.writeAllImportant(user);
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();

        try {
            List<List<String>> importants = jdbcsQlite.getAllUserImportantTasksByUserName("abby");
            assertEquals("abby", importants.get(0).get(2));
            assertEquals("homework", importants .get(0).get(3));
            assertEquals("2021-12-10", importants .get(0).get(4));
            jdbcsQlite.deleteUserImportantListByTaskID(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }

    @Test
    public void TestGetAllImportant() {
        ImportantGateway.getAllImportant(user.getName());
        assertEquals("homework", user.getImportant().getScheduleLists().get(0).get(0));
        assertEquals("2021-12-10", user.getImportant().getScheduleLists().get(0).get(1));
    }
}