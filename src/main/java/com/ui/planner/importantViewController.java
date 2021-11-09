package com.ui.planner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class importantViewController implements Initializable {
    @FXML
    private TableColumn importantCol1;
    @FXML
    private TableColumn importantCol2;
    @FXML
    private TableColumn importantCol3;
    @FXML
    private Button importantNewEventBtn;

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
        // TODO: Change the column name.
        importantCol1.setText("Events (Change me)");
        importantCol2.setText("Start (Change me)");
        importantCol3.setText("End (Chang me)");
    }

    @FXML
    protected void newEventBtnMouseEntered() {
        importantNewEventBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void newEventBtnClicked() throws IOException {
        AddImportantView addImportantView = new AddImportantView();
        addImportantView.showWindow();
    }



}
