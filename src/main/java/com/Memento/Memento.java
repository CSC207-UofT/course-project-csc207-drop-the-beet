package com.memento;

import java.io.Serializable;
import java.util.HashMap;

public class Memento implements Serializable {
    private HashMap state;

    void setState(HashMap state) {
        this.state = state;
    }

    HashMap getState() {
        return state;
    }
}
