package com.ui.planner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IncomingViewController implements Initializable {
    @FXML
    private TableColumn incomingCol1;
    @FXML
    private TableColumn incomingCol2;
    @FXML
    private TableColumn incomingCol3;

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
        incomingCol1.setText("Events (Change me)");
        incomingCol2.setText("Start (Change me)");
        incomingCol3.setText("End (Change me)");
    }

    @FXML
    protected void onNewEventClicked() throws IOException {
        AddIncomingApplication addIncomingApplication = new AddIncomingApplication();
        addIncomingApplication.showWindow();
    }
}
