package com.ui.planner;

import com.planner.Connection.InfoReadWriter;
import com.planner.SchedulesController;
import com.planner.ToDoListsController;
import com.planner.UserController;
import com.planner.UserManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import com.database.JDBCSQlite;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Button dashboardBtn;
    @FXML
    private Button incomingBtn;
    @FXML
    private Button todolistBtn;
    @FXML
    private Button importantBtn;
    @FXML
    private Button signoutBtn;
    @FXML
    private Button settingsBtn;
    @FXML
    private Label todoNumLabel;
    @FXML
    private Label scheduledNumLabel;
    @FXML
    private Label importantNumLabel;
    @FXML
    private StackPane innerStackPane;
    @FXML
    private AnchorPane todoListBubble;
    @FXML
    private AnchorPane allBubble;
    @FXML
    private AnchorPane importantBubble;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private Parent incomingView;
    @FXML
    private Parent todoListView;
    @FXML
    private Parent importantView;
    @FXML
    private Parent settingsView;
    @FXML
    private TableColumn<ToDoEventModel, String> event;
    @FXML
    private TableColumn<ToDoEventModel, String> end;
    @FXML
    private TableView<ToDoEventModel> todayTable;
    @FXML
    ArrayList<Button> arrBtn = new ArrayList<Button>();

    private ArrayList<ArrayList<String>> allUserToDoTasks;
    private ArrayList<ArrayList<String>> allUserSchedules;
    private ArrayList<ArrayList<String>> allUserImportantTasks;

    private ObservableList<ToDoEventModel> toDoEventModels = FXCollections.observableArrayList();

    private JDBCSQlite jdbcsQlite;

    private UserManager currUser;


    public void setUser(String userName, String userEmail, String passWord) {
        jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        System.out.println(userName);
        System.out.println(userEmail);
        System.out.println(passWord);

        currUser = new UserManager(userName, userEmail, passWord);//done
        int scheduleNum = SchedulesController.loadScheduleBubble(currUser, jdbcsQlite);
        int todoNum = ToDoListsController.loadTodoBubble(currUser, jdbcsQlite);
        int importantNum = InfoReadWriter.loadImportantBubble(currUser, jdbcsQlite);

        todoNumLabel.setText(Integer.toString(todoNum));

        scheduledNumLabel.setText(Integer.toString(scheduleNum));

        importantNumLabel.setText(Integer.toString(importantNum));

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("incoming-view.fxml"));
            incomingView = fxmlLoader.load();
            IncomingViewController incomingViewController = (IncomingViewController) fxmlLoader.getController();
            incomingViewController.setUser(currUser);//done
            incomingViewController.showEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(incomingView);


        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("todoList-view.fxml"));
            todoListView = fxmlLoader.load();
            TodoListViewController todoListViewController = (TodoListViewController) fxmlLoader.getController();
            todoListViewController.setUser(currUser);//done
            todoListViewController.showEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(todoListView);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("important-view.fxml")));
            importantView = fxmlLoader.load();
            ImportantViewController importantViewController = (ImportantViewController) fxmlLoader.getController();
            importantViewController.setUser(currUser);//done
            importantViewController.showEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(importantView);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
            settingsView = fxmlLoader.load();
            SettingsViewController settingsViewController = (SettingsViewController) fxmlLoader.getController();
            settingsViewController.setUser(currUser);//done
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(settingsView);

        dashboardPane.toFront();
        showEvents();
    }

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
        if (arrBtn.size() == 0) {
            arrBtn.add(dashboardBtn);
            arrBtn.add(incomingBtn);
            arrBtn.add(todolistBtn);
            arrBtn.add(importantBtn);
            arrBtn.add(settingsBtn);
            arrBtn.add(settingsBtn);
        }
        event.setCellValueFactory(new PropertyValueFactory<>("Event"));
        end.setCellValueFactory(new PropertyValueFactory<>("End"));
    }

    @FXML
    public void showEvents() {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        ArrayList<ArrayList<String>> lst = null;
        try{
            lst = jdbcsQlite.getAllUserToDoTasksTodayByUserName(currUser.getName());
            //return lst
        } catch (SQLException e) {
            e.printStackTrace();
        } //Todo 210 217

        if (lst != null && lst.size() >= 1) {
            for (ArrayList<String> l : lst){
                toDoEventModels.add(new ToDoEventModel(l.get(3), l.get(4)));
            }
            todayTable.setItems(toDoEventModels);
        }
        jdbcsQlite.close();
    }

    private void refreshUserStatus() {
        UserController.loadModifyUser(currUser);
    }

    private void refreshBubbleStatus() {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        int scheduleNum = SchedulesController.loadScheduleBubble(currUser, jdbcsQlite);
        int todoNum = ToDoListsController.loadTodoBubble(currUser, jdbcsQlite);
        int importantNum = InfoReadWriter.loadImportantBubble(currUser, jdbcsQlite);
        jdbcsQlite.close();
            todoNumLabel.setText(Integer.toString(todoNum));

            scheduledNumLabel.setText(Integer.toString(scheduleNum));

            importantNumLabel.setText(Integer.toString(importantNum));

    }

    /**
     * Called to refresh all button status after a button is clicked
     *
     * @param curr The button that is clicked
     */
    @FXML
    private void refreshBtnStatus(Button curr) {
        curr.setUnderline(true);
        for (Button btn: arrBtn) {
            System.out.println(btn);
            if (!btn.equals(curr)) {
                btn.setUnderline(false);
            }
        }
    }

    /**
     * Called when the dashboard button is clicked.
     */
    @FXML
    protected void ondashboardBtnClicked() {
        dashboardPane.toFront();
        refreshBtnStatus(dashboardBtn);
        refreshUserStatus();
        refreshBubbleStatus();
        toDoEventModels.clear();
        showEvents();
        System.out.println("dashboard btn clicked");
    }

    /**
     * Called when the mouse enters the dashboard button
     */
    @FXML
    protected void ondashboardBtnMouseEntered() {
        dashboardBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onincomingBtnClicked() {
        incomingView.toFront();
        refreshBtnStatus(incomingBtn);
        refreshUserStatus();
        refreshBubbleStatus();
        System.out.println("incoming btn clicked!");

    }

    @FXML
    protected void onincomingBtnMouseEntered() {
        incomingBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void ontodolistClicked() {
        todoListView.toFront();
        refreshBtnStatus(todolistBtn);
        refreshUserStatus();
        refreshBubbleStatus();
        System.out.println("to do list btn clicked");
    }

    @FXML
    protected void ontodolistMouseEntered() {
        todolistBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onimportantBtnClicked() {
        importantView.toFront();
        refreshBtnStatus(importantBtn);
        refreshUserStatus();
        refreshBubbleStatus();
        System.out.println("Important Btn Clicked");
    }

    @FXML
    protected void onimportantBtnMouseEntered() {
        importantBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onsignoutBtnClicked() {
        refreshBtnStatus(signoutBtn);
        refreshUserStatus();
        refreshBubbleStatus();
        jdbcsQlite.close();
        Platform.exit();
    }

    @FXML
    protected void onsignoutBtnMouseEntered() {
        signoutBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onsettingsBtnClicked() {
        settingsView.toFront();
        refreshUserStatus();
        refreshBubbleStatus();
        refreshBtnStatus(settingsBtn);
        System.out.println("Settings clicked!");
    }

    @FXML
    protected void onsettingsBtnMouseEntered() {
        settingsBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onToDoBubbleMouseEntered() {todoListBubble.setCursor(Cursor.HAND);}

    @FXML
    protected void onAllBubbleMouseEntered() {allBubble.setCursor(Cursor.HAND);}

    @FXML
    protected void onImportantBubbleMouseEntered() {importantBubble.setCursor(Cursor.HAND);}

    @FXML
    protected void ontodoBubbleClicked() {
        ontodolistClicked();
    }

    @FXML
    protected void onScheduledBubbleClicked() {
        onincomingBtnClicked();
    }

    @FXML
    protected void onImportantBubbleClicked() {
        onimportantBtnClicked();
    }
}
