package com.ui.planner;

import com.planner.UseCases.UserManager;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    ArrayList<Button> arrBtn = new ArrayList<>();

    private final ObservableList<ToDoEventModel> toDoEventModels = FXCollections.observableArrayList();

    private UserManager currUser;

    /**
     * set up user
     * @param user from previous step
     */
    public void setUser(UserManager user) {
        currUser = user;
        refreshBubbleStatus();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("incoming-view.fxml"));
            incomingView = fxmlLoader.load();
            IncomingViewController incomingViewController = fxmlLoader.getController();
            incomingViewController.setUser(user);//done
            incomingViewController.showEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(incomingView);


        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("todoList-view.fxml"));
            todoListView = fxmlLoader.load();
            TodoListViewController todoListViewController = fxmlLoader.getController();
            todoListViewController.setUser(user);//done
            todoListViewController.showEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(todoListView);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("important-view.fxml")));
            importantView = fxmlLoader.load();
            ImportantViewController importantViewController = fxmlLoader.getController();
            importantViewController.setUser(user);//done
            importantViewController.showEvents();
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(importantView);

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
            settingsView = fxmlLoader.load();
            SettingsViewController settingsViewController = fxmlLoader.getController();
            settingsViewController.setUser(user);//done
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

    /**
     * show TodoLists that are due today
     */
    @FXML
    public void showEvents() {
        List<List<String>> lst = currUser.getToDoLists().getToDoListLists();
        List<List<String>> todayList = new ArrayList<>();

        if (lst != null) {
            for (List<String> l : lst) {
                if (l.get(1).equals(LocalDate.now().toString())) {
                    todayList.add(l);
                }
            }

            if (todayList.size() >= 1) {
                for (List<String> l : todayList) {
                    toDoEventModels.add(new ToDoEventModel(l.get(0), l.get(1)));
                }
                todayTable.setItems(toDoEventModels);
            }
        }
    }

    /**
     * set user new info
     */
    private void refreshUserStatus() {
        currUser.setEmail(currUser.getEmail());
        currUser.setPassword(currUser.getPassword());
    }

    private void refreshBubbleStatus() {

        int scheduleNum = currUser.getSchedules().getSize();
        int todoNum = currUser.getToDoLists().getSize();
        int importantNum = currUser.getImportant().getSize();

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

    /**
     * when schedule button is clicked
     */
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

    /**
     * when todolist button is clicked
     */
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

    /**
     * when important button is clicked
     */
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

    /**
     * when sign out button is clicked
     */
    @FXML
    protected void onsignoutBtnClicked() {
        refreshBtnStatus(signoutBtn);
        refreshUserStatus();
        refreshBubbleStatus();
        Platform.exit();
    }

    @FXML
    protected void onsignoutBtnMouseEntered() {
        signoutBtn.setCursor(Cursor.HAND);
    }

    /**
     * when setting button is clicked
     */
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
