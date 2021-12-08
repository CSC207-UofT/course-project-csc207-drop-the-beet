package com.planner.UseCases;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    UserManager um;
    ScheduleManager s1;
    ToDoListManager t1;
    ScheduleManager i1;

    @BeforeEach
    void setUp() {
        um = new UserManager("Tom", "1@1.com", "1234");
        s1 = new ScheduleManager();
        t1 = new ToDoListManager();
        i1 = new ScheduleManager();
        s1.addSchedule(LocalDate.parse("2021-01-01"), LocalDate.parse("2022-01-01"), "Sleep");
        t1.addTask("Deep sleep", LocalDate.parse("2022-01-01"));
        i1.addSchedule(LocalDate.parse("2021-05-01"), LocalDate.parse("2022-08-01"), "Sleep well");
    }

    @Test
    void getName() {
        assertEquals("Tom", um.getName());
    }

    @Test
    void getEmail() {
        assertEquals("1@1.com", um.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("1234", um.getPassword());
    }

    @Test
    void setEmail() {
        um.setEmail("2@2.com");
        assertEquals("2@2.com", um.getEmail());
    }

    @Test
    void setPassword() {
        um.setPassword("4321");
        assertEquals("4321", um.getPassword());
    }

    @Test
    void setSchedules() {
        assertEquals(0, um.schedules.getSize());
        um.setSchedules(s1);
        assertEquals(1, um.schedules.getSize());
    }

    @Test
    void setToDoLists() {
        assertEquals(0, um.toDoLists.getSize());
        um.setToDoLists(t1);
        assertEquals(1, um.toDoLists.getSize());
    }

    @Test
    void setImportant() {
        assertEquals(0, um.schedules.getSize());
        um.setSchedules(i1);
        assertEquals(1, um.schedules.getSize());
    }

    @Test
    void getSchedules() {
        um.setSchedules(s1);
        ScheduleManager sm1 = um.getSchedules();
        List<String> scheduleList = sm1.getScheduleLists().get(0);  // Only 1 element
        assertEquals("Sleep", scheduleList.get(0));
        assertEquals("2021-01-01", scheduleList.get(1));
        assertEquals("2022-01-01", scheduleList.get(2));
    }

    @Test
    void getToDoLists() {
        um.setToDoLists(t1);
        ToDoListManager td1 = um.getToDoLists();
        List<String> todolistList = td1.getToDoListLists().get(0);
        assertEquals("Deep sleep", todolistList.get(0));
        assertEquals("2022-01-01", todolistList.get(1));
    }

    @Test
    void getImportant() {
        um.setImportant(i1);
        ScheduleManager important1 = um.getImportant();
        List<String> importantList = important1.getScheduleLists().get(0);  // Only 1 element
        assertEquals("Sleep well", importantList.get(0));
        assertEquals("2021-05-01", importantList.get(1));
        assertEquals("2022-08-01", importantList.get(2));
    }
}
