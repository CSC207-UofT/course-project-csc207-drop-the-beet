package com.planner.UseCases;

import com.planner.Entities.ToDoList;
import com.planner.UseCases.ToDoListManager;
import org.junit.Before;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ToDoListManagerTest {
    ToDoListManager toDoListManager;

    @Before
    public void setUp(){
    }

    @org.junit.jupiter.api.Test
    public void TestAddTask() {
        toDoListManager = new ToDoListManager();
        toDoListManager.addTask("csc207", LocalDate.parse("2021-12-17"));
        assertEquals("csc207", toDoListManager.getToDos().get(0).getTask());
    }

    @org.junit.jupiter.api.Test
    public void TestGetSize() {
        toDoListManager = new ToDoListManager();
        toDoListManager.addTask("csc207", LocalDate.parse("2021-12-17"));
        toDoListManager.addTask("project due", LocalDate.parse("2021-12-08"));
        assertEquals(2, toDoListManager.getSize());
    }

    @org.junit.jupiter.api.Test
    public void testGetToDoListLists() {
        toDoListManager = new ToDoListManager();
        toDoListManager.addTask("csc207", LocalDate.parse("2021-12-17"));
        toDoListManager.addTask("project due", LocalDate.parse("2021-12-08"));
        assertEquals("csc207", toDoListManager.getToDoListLists().get(0).get(0));
        assertEquals("project due", toDoListManager.getToDoListLists().get(1).get(0));
    }

    @org.junit.jupiter.api.Test
    public void testSetToDos() {
        toDoListManager = new ToDoListManager();
        List<List<String>> toDoListLists = new ArrayList<>();
        List<String> toDoLists = new ArrayList<>();
        toDoLists.add("1");
        toDoLists.add("2");
        toDoLists.add("abby");
        toDoLists.add("file");
        toDoLists.add("2021-12-12");
        toDoListLists.add(toDoLists);
        toDoListManager.setToDos(toDoListLists);
        assertEquals(1, toDoListManager.getSize());
        assertEquals("file", toDoListManager.getToDos().get(0).getTask());
        assertEquals("2021-12-12", toDoListManager.getToDos().get(0).getDeadline().toString());
    }
}
