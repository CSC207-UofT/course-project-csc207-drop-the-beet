package com.Memento;

import java.util.ArrayList;
import java.util.List;

/**
 * caretaker for memento
 */

public class Caretaker {
    private final List<Memento> mementoList = new ArrayList<>();

    /**
     * add a memento state
     * @param state memento state of ToDoListManager
     */
    public void add(Memento state){
        mementoList.add(state);
    }

    /**
     * get the memento state at the index
     * @param index the index number of the list
     * @return the memento at the index
     */
    public Memento get(int index){
        return mementoList.get(index);
    }
}
