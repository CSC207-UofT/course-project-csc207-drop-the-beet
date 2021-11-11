package com;

import com.ui.planner.*;
import javafx.application.Application;


public class Driver {
    public static void main(String[] args) {
        new Thread(() -> Application.launch(LoginView.class)).start();
    }

}

