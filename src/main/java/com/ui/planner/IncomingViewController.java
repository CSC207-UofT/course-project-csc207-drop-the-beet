package com.ui.planner;

import com.database.JDBCSQlite;
import com.planner.UseCases.UserManager;
import com.ui.planner.ScheduleEventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IncomingViewController implements Initializable {
    @FXML
    private TableColumn<ScheduleEventModel, String> event;
    @FXML
    private TableColumn<ScheduleEventModel, String> start;
    @FXML
    private TableColumn<ScheduleEventModel, String> end;
    @FXML
    private TableView<ScheduleEventModel> inComingTb;

    private UserManager user; //done

    private ObservableList<ScheduleEventModel> scheduleEventModels = FXCollections.observableArrayList();


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
        event.setCellValueFactory(new PropertyValueFactory<>("Event"));
        start.setCellValueFactory(new PropertyValueFactory<>("Start"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));
    }

    @FXML
    public void showEvents() {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        ArrayList<ArrayList<String>> lst = null;
        try{
            lst = jdbcsQlite.getAllUserEventTasksByUserName(user.getName());//done
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (lst != null && lst.size() > 0) {
            for (ArrayList<String> l : lst){
                scheduleEventModels.add(new ScheduleEventModel(l.get(3), l.get(4), l.get(5)));
            }
            inComingTb.setItems(scheduleEventModels);
        }
        jdbcsQlite.close();
    }


    @FXML
    protected void onNewEventClicked() throws IOException {
        AddIncomingApplication addIncomingApplication = new AddIncomingApplication();
        addIncomingApplication.setUser(user);//done
        addIncomingApplication.showWindow();
        scheduleEventModels.clear();
        showEvents();
    }

    public void setUser(UserManager currUser) {
        user = currUser;
    }
}
