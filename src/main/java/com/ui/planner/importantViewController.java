package com.ui.planner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class importantViewController implements Initializable {
    @FXML
    private TableColumn importantCol1;
    @FXML
    private TableColumn importantCol2;
    @FXML
    private TableColumn importantCol3;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        importantCol1.setText("Events (Change me)");
        importantCol2.setText("Start (Change me)");
        importantCol3.setText("End (Chang me)");
    }
}
