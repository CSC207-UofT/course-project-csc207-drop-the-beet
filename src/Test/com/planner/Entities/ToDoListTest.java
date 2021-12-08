package com.planner.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    ToDoList toDoList;

    @BeforeEach
    void setUp() {
        toDoList = new ToDoList("homework", LocalDate.parse("2021-12-10"));
    }

    @Test
    void TestGetTask() {
        assertEquals("homework", toDoList.getTask());
    }

    @Test
    void TestGetDeadline() {
        assertEquals(LocalDate.parse("2021-12-10"), toDoList.getDeadline());
    }

    @Test
    void TestToString() {
        assertEquals("homework", toDoList.getTask());
    }
}