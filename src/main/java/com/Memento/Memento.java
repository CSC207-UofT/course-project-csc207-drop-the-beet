package com.Memento;

import com.planner.Controller.ToDoListsController;
import com.planner.UseCases.ToDoListManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Memento implements Serializable {
    private ToDoListManager state;

    public Memento(ToDoListManager state) {
    }

    void setState(ToDoListManager state) {
        this.state = state;
    }

    public ToDoListManager getState() {
        return state;
    }
}
