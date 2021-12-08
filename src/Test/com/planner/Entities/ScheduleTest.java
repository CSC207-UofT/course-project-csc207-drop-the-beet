package com.planner.Entities;

import java.time.LocalDate;

import com.planner.UseCases.ScheduleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    Schedule s;
    LocalDate start;
    LocalDate end;

    @BeforeEach
    void setUp() {
        start = LocalDate.parse("2020-01-01");
        end = LocalDate.parse("2021-01-01");
        s = new Schedule(start, end, "Sleep");
    }

    @Test
    void getTask() {
        assertEquals("Sleep", s.getTask());
    }

    @Test
    void getStart() {
        assertEquals(start, s.getStart());
    }

    @Test
    void getEnd() {
        assertEquals(end, s.getEnd());
    }

    @Test
    void testToString() {
        assertEquals("Sleep", s.toString());
    }
}