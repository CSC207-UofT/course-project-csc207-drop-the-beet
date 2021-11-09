package com.ui.planner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class OverViewController implements Initializable {
    @FXML
    private TableColumn overViewCol1;
    @FXML
    private TableColumn overViewCol2;
    @FXML
    private TableColumn overViewCol3;

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
        overViewCol1.setText("Events (Change me)");
        overViewCol2.setText("Start (Change me)");
        overViewCol3.setText("End (Change me)");
    }
}
