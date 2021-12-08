package com.planner.Gateway;

import com.database.JDBCSQlite;
import com.planner.UseCases.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchedulesGatewayTest {
    UserManager user;

    /**
     * "abby", "abby@mail.com", "1111" has been written in database
     */
    @BeforeEach
    public void setUp() {
        user = new UserManager("abby", "abby@mail.com", "1111");
        UserGateway.writeAllUserInfo(user);
        user.getSchedules().addSchedule(LocalDate.parse("2021-12-10"), LocalDate.parse("2021-12-10"), "homework");
    }


    @Test
    public void TestWriteAllSchedule() {
        SchedulesGateway.writeAllSchedule(user);
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();

        try {
            List<List<String>> schedules = jdbcsQlite.getAllUserEventTasksByUserName("abby");
            assertEquals("abby", schedules.get(0).get(2));
            assertEquals("homework", schedules.get(0).get(3));
            assertEquals("2021-12-10", schedules.get(0).get(4));
            jdbcsQlite.deleteUserEventListByTaskID(1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }

    @Test
    public void TestGetAllSchedule() {
        SchedulesGateway.getAllSchedule(user.getName());
        assertEquals("homework", user.getSchedules().getScheduleLists().get(0).get(0));
        assertEquals("2021-12-10", user.getSchedules().getScheduleLists().get(0).get(1));
    }

}