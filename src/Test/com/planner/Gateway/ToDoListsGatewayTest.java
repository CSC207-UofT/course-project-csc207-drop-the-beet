package com.planner.Gateway;

import com.database.DBTodoList;
import com.planner.UseCases.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListsGatewayTest {
    UserManager user;

    /**
     * "abby", "abby@mail.com", "1111" has been written in database
     */
    @BeforeEach
    public void setUp() {
        user = new UserManager("abby", "abby@mail.com", "1111");
        UserGateway.writeAllUserInfo(user);
        user.getToDoLists().addTask("homework", LocalDate.parse("2021-12-10"));
        user.getToDoLists().addTask("project due", LocalDate.parse("2021-12-08"));
    }


    @Test
    public void TestWriteAllToDoList() {

        ToDoListsGateway.writeAllToDoList(user);
        DBTodoList jdbcsQlite = new DBTodoList();
        jdbcsQlite.create();

        try {
            List<List<String>> toDosLists = jdbcsQlite.getAllUserToDoTasksByUserName("abby");
            assertEquals("abby", toDosLists.get(0).get(2));
            assertEquals("homework", toDosLists.get(0).get(3));
            assertEquals("2021-12-10", toDosLists.get(0).get(4));
            assertEquals("abby", toDosLists.get(1).get(2));
            assertEquals("project due", toDosLists.get(1).get(3));
            assertEquals("2021-12-08", toDosLists.get(1).get(4));
            jdbcsQlite.deleteUserToDoListByTaskID(1);
            jdbcsQlite.deleteUserToDoListByTaskID(2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
    }

    @Test
    public void TestGetAllToDoLists() {
        ToDoListsGateway.getAllToDoLists(user.getName());
        assertEquals("homework", user.getToDoLists().getToDos().get(0).getTask());
        assertEquals("2021-12-10", user.getToDoLists().getToDos().get(0).getDeadline().toString());
    }
}