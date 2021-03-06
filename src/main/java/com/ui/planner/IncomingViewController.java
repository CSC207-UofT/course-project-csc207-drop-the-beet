package com.ui.planner;

import com.planner.UseCases.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    private final ObservableList<ScheduleEventModel> scheduleEventModels = FXCollections.observableArrayList();


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

    /**
     * show all schedules
     */
    @FXML
    public void showEvents() {
        List<List<String>> lst;
        lst = user.getSchedules().getScheduleLists();

        if (lst != null && lst.size() > 0) {
            for (List<String> l : lst){
                scheduleEventModels.add(new ScheduleEventModel(l.get(0), l.get(1), l.get(2)));
            }
            inComingTb.setItems(scheduleEventModels);
        }

    }

    /**
     * when add new is clicked
     * @throws IOException database access error
     */
    @FXML
    protected void onNewEventClicked() throws IOException {
        AddIncomingApplication addIncomingApplication = new AddIncomingApplication();
        addIncomingApplication.setUser(user);//done
        addIncomingApplication.showWindow();
        scheduleEventModels.clear();
        showEvents();
    }

    /**
     * setup user
     * @param currUser sent from previous step
     */
    public void setUser(UserManager currUser) {
        user = currUser;
    }
}
