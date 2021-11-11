package com.ui.planner;

import com.planner.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import com.datebase.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Button dashboardBtn;
    @FXML
    private Button incomingBtn;
    @FXML
    private Button overviewBtn;
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
    private Parent overviewView;
    @FXML
    private Parent todoListView;
    @FXML
    private Parent importantView;
    @FXML
    private Parent settingsView;
    @FXML
    private TableColumn todayCol1;
    @FXML
    private TableColumn todayCol2;
    @FXML
    private TableColumn todayCol3;
    @FXML
    private TableView todayTable;
    @FXML
    ArrayList<Button> arrBtn = new ArrayList<Button>();

    private ArrayList<ArrayList<String>> allUserToDoTasks;
    private ArrayList<ArrayList<String>> allUserSchedules;
    private ArrayList<ArrayList<String>> allUserImportantTasks;

    private JDBCSQlite jdbcsQlite;

    private User currUser;


    public void setUser(String userName, String userEmail, String passWord) {
        jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        System.out.println(userName);
        System.out.println(userEmail);
        System.out.println(passWord);

        currUser = new User(userName, userEmail, passWord);
        try {
            allUserToDoTasks = jdbcsQlite.getAllUserToDoTasksByUserName(currUser.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            allUserSchedules = jdbcsQlite.getAllUserEventTasksByUserName(currUser.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            allUserImportantTasks = jdbcsQlite.getAllUserImportantTasksByUserName(currUser.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int todoNum;
        if (allUserToDoTasks == null) {
            todoNum = 0;
        } else {
            todoNum = allUserToDoTasks.size();
        }

        int scheduleNum;
        if (allUserSchedules == null) {
            scheduleNum = 0;
        } else {
            scheduleNum = allUserSchedules.size();
        }

        int importantNum;
        if (allUserImportantTasks == null) {
            importantNum = 0;
        } else {
            importantNum = allUserImportantTasks.size();
        }

        todoNumLabel.setText(Integer.toString(todoNum));

        scheduledNumLabel.setText(Integer.toString(scheduleNum));

        importantNumLabel.setText(Integer.toString(importantNum));

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("incoming-view.fxml"));
            incomingView = fxmlLoader.load();
            IncomingViewController incomingViewController = (IncomingViewController) fxmlLoader.getController();
            incomingViewController.setUser(currUser);
            incomingViewController.showEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(incomingView);

        try{
            overviewView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("overview-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(overviewView);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("todoList-view.fxml"));
            todoListView = fxmlLoader.load();
            TodoListViewController todoListViewController = (TodoListViewController) fxmlLoader.getController();
            todoListViewController.setUser(currUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(todoListView);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("important-view.fxml")));
            importantView = fxmlLoader.load();
            ImportantViewController importantViewController = (ImportantViewController) fxmlLoader.getController();
            importantViewController.setUser(currUser);
            importantViewController.showEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(importantView);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
            settingsView = fxmlLoader.load();
            SettingsViewController settingsViewController = (SettingsViewController) fxmlLoader.getController();
            settingsViewController.setUser(currUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(settingsView);

        dashboardPane.toFront();
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
        // Change Table Today's first column
        todayCol1.setText("Events (Change me)");
        // Change Table Today's second column
        todayCol2.setText("Start (Change me)");
        // Change Table Today's third column
        todayCol3.setText("End (Change me)");

        if (arrBtn.size() == 0) {
            arrBtn.add(dashboardBtn);
            arrBtn.add(incomingBtn);
            arrBtn.add(overviewBtn);
            arrBtn.add(todolistBtn);
            arrBtn.add(importantBtn);
            arrBtn.add(settingsBtn);
            arrBtn.add(settingsBtn);
        }
    }

    private void refreshUserStatus() {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try{
            currUser.setEmail(jdbcsQlite.getUserEmail(currUser.getName()));
            currUser.setPassword(jdbcsQlite.getUserPassword(currUser.getName()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        jdbcsQlite.close();
    }

    private void refreshBubbleStatus() {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();
        try {
            allUserToDoTasks = jdbcsQlite.getAllUserToDoTasksByUserName(currUser.getName());
            allUserImportantTasks = jdbcsQlite.getAllUserImportantTasksByUserName(currUser.getName());
            allUserSchedules = jdbcsQlite.getAllUserEventTasksByUserName(currUser.getName());

            int todoNum;
            if (allUserToDoTasks == null) {
                todoNum = 0;
            } else {
                todoNum = allUserToDoTasks.size();
            }

            int scheduleNum;
            if (allUserSchedules == null) {
                scheduleNum = 0;
            } else {
                scheduleNum = allUserSchedules.size();
            }

            int importantNum;
            if (allUserImportantTasks == null) {
                importantNum = 0;
            } else {
                importantNum = allUserImportantTasks.size();
            }

            todoNumLabel.setText(Integer.toString(todoNum));

            scheduledNumLabel.setText(Integer.toString(scheduleNum));

            importantNumLabel.setText(Integer.toString(importantNum));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jdbcsQlite.close();
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
    protected void onoverviewBtnClicked() {
        overviewView.toFront();
        refreshBtnStatus(overviewBtn);
        refreshUserStatus();
        refreshBubbleStatus();
        System.out.println("all plans btn clicked");
    }

    @FXML
    protected void onoverviewBtnMouseEntered() {
        overviewBtn.setCursor(Cursor.HAND);
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
    protected void onOverviewBubbleClicked() {
        onincomingBtnClicked();
    }

    @FXML
    protected void onImportantBubbleClicked() {
        onimportantBtnClicked();
    }
}
