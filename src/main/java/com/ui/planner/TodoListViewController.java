package com.ui.planner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TodoListViewController implements Initializable {
    @FXML
    private TableColumn todolistCol1;
    @FXML
    private TableColumn todolistCol2;
    @FXML
    private TableColumn todolistCol3;
    @FXML
    private Button todoListNewEventBtn;

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

    @FXML
    protected void todoListNewEventBtnClicked() throws IOException {
        AddtodoListView addtodoList = new AddtodoListView();
        addtodoList.showWindow();
    }

}
