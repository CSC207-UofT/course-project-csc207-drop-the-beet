package com.Memento;

import com.planner.UseCases.ToDoListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MementoTest {
    ToDoListManager state;
    Memento m;

    @BeforeEach
    void setUp() {
        state = new ToDoListManager();
        state.addTask("csc207", LocalDate.parse("2021-12-17"));
        m = new Memento(state);
    }

    @Test
    void TestGetState() {
        assertNull(m.getState());
    }

    @Test
    void TestSetState() {
        m.setState(state);
        assertEquals(state, m.getState());
    }
}
