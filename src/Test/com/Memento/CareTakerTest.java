package com.Memento;

import com.planner.UseCases.ToDoListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CareTakerTest {
    Memento m;
    ToDoListManager todo;
    Caretaker c;

    @BeforeEach
    void setUp() {
        todo = new ToDoListManager();
        todo.addTask("csc207", LocalDate.parse("2021-12-17"));
        m = new Memento(todo);
        c = new Caretaker();
    }

    @Test
    void TestGeneral(){
        c.add(m);
        assertNull(c.get(0).getState());
    }
}


