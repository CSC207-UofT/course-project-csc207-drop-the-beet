package com.ui.planner;

import com.datebase.JDBCSQlite;
import com.planner.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ImportantViewController implements Initializable {
    @FXML
    private TableColumn<ImportantEventModel, String> event;
    @FXML
    private TableColumn<ImportantEventModel, String> start;
    @FXML
    private TableColumn<ImportantEventModel, String> end;
    @FXML
    private TableView<ImportantEventModel> importantEventTb;
    @FXML
    private Button importantNewEventBtn;

    private ObservableList<ImportantEventModel> importantEventModels = FXCollections.observableArrayList();

    private User user;


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
        event.setCellValueFactory(new PropertyValueFactory<>("Event"));
        start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));

        ;
    }

    @FXML
    public void showEvents() {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        ArrayList<ArrayList<String>> lst = null;
        try{
             lst = jdbcsQlite.getAllUserImportantTasksByUserName(user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (lst != null && lst.size() > 0) {
            for (ArrayList<String> l : lst){
                importantEventModels.add(new ImportantEventModel(l.get(3), l.get(4), l.get(5)));
            }
            importantEventTb.setItems(importantEventModels);
        }
    }

    @FXML
    protected void newEventBtnMouseEntered() {
        importantNewEventBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void newEventBtnClicked() throws IOException {
        AddImportantView addImportantView = new AddImportantView();
        addImportantView.setUser(user);
        addImportantView.showWindow();
        importantEventModels.clear();
        showEvents();
    }


    public void setUser(User currUser) {
        user = currUser;
    }
}
