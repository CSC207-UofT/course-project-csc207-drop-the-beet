package com.planner.UseCases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class ScheduleManagerTest {
    ScheduleManager sm;
    LocalDate start;
    LocalDate end;

    @BeforeEach
    public void setUp() {
        sm = new ScheduleManager();
        start = LocalDate.parse("2020-01-01");
        end = LocalDate.parse("2021-01-01");
        sm.addSchedule(start, end, "Eat");
        sm.addSchedule(start, end, "Sleep");
        sm.addSchedule(start, end, "Code");
        sm.addSchedule(start, end, "Repeat");
    }

    @Test
    void TestGetScheduleLists() {
        Assertions.assertEquals("Eat", sm.getScheduleLists().get(0).get(0));
        Assertions.assertEquals("2020-01-01", sm.getScheduleLists().get(0).get(1));
        Assertions.assertEquals("2021-01-01", sm.getScheduleLists().get(0).get(2));
        Assertions.assertEquals("Sleep", sm.getScheduleLists().get(1).get(0));
        Assertions.assertEquals("2020-01-01", sm.getScheduleLists().get(1).get(1));
        Assertions.assertEquals("2021-01-01", sm.getScheduleLists().get(1).get(2));
        Assertions.assertEquals("Code", sm.getScheduleLists().get(2).get(0));
        Assertions.assertEquals("2020-01-01", sm.getScheduleLists().get(2).get(1));
        Assertions.assertEquals("2021-01-01", sm.getScheduleLists().get(2).get(2));
        Assertions.assertEquals("Repeat", sm.getScheduleLists().get(3).get(0));
        Assertions.assertEquals("2020-01-01", sm.getScheduleLists().get(3).get(1));
        Assertions.assertEquals("2021-01-01", sm.getScheduleLists().get(3).get(2));
    }

    @Test
    void TestAddSchedule() {
        Assertions.assertEquals("Eat", sm.getScheduleLists().get(0).get(0));
        Assertions.assertEquals("2020-01-01", sm.getScheduleLists().get(0).get(1));
        Assertions.assertEquals("2021-01-01", sm.getScheduleLists().get(0).get(2));
    }

    @Test
    void TestGetSize() {
        Assertions.assertEquals(4, sm.getSize());
    }

    @Test
    void TestSetSchedules() {
        List<List<String>> bigList = new ArrayList<>();
        List<String> smallList1 = new ArrayList<>();
        List<String> smallList2 = new ArrayList<>();
        smallList1.add("todoID1");
        smallList1.add("userID1");
        smallList1.add("Eat");
        smallList1.add("2020-01-01");
        smallList1.add("2021-01-01");

        smallList2.add("todoID1");
        smallList2.add("userID1");
        smallList2.add("Sleep");
        smallList2.add("2020-01-01");
        smallList2.add("2021-01-01");

        bigList.add(smallList1);
        bigList.add(smallList2);
        sm.setSchedules(bigList);

        Assertions.assertEquals("Eat", sm.getScheduleLists().get(0).get(0));
        Assertions.assertEquals("2020-01-01", sm.getScheduleLists().get(0).get(1));
        Assertions.assertEquals("2021-01-01", sm.getScheduleLists().get(0).get(2));
        Assertions.assertEquals("Sleep", sm.getScheduleLists().get(1).get(0));
        Assertions.assertEquals("2020-01-01", sm.getScheduleLists().get(1).get(1));
        Assertions.assertEquals("2021-01-01", sm.getScheduleLists().get(1).get(2));

    }
}