package com.ui.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class todoListViewController implements Initializable {
    @FXML
    private TableColumn todolistCol1;
    @FXML
    private TableColumn todolistCol2;
    @FXML
    private TableColumn todolistCol3;

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
        todolistCol1.setText("Events (Change me)");
        todolistCol2.setText("Start (Change me)");
        todolistCol3.setText("End (Change me)");
    }
}
