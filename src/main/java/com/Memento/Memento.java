package com.Memento;

import com.planner.UseCases.ToDoListManager;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * memento class
 */

public class Memento implements Serializable {
    private ToDoListManager state;

    /**
     * Constructor of Memento
     *
     * @param state ToDoListManager state
     */
    public Memento(ToDoListManager state) {
    }

    /**
     * set the state
     *
     * @param state ToDoListManager state
     */
    void setState(ToDoListManager state) {
        this.state = state;
    }

    /**
     * get the state
     *
     * @return ToDoListManager state
     */
    public ToDoListManager getState() {
        return state;
    }


}
