package com.ui.planner;

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

import java.io.IOException;
import java.net.URL;
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
    private Label todoProgressLabel;
    @FXML
    private Label pastNumLabel;
    @FXML
    private Label pastProgressLabel;
    @FXML
    private Label importantNumLabel;
    @FXML
    private Label importantProgressLabel;
    @FXML
    private StackPane innerStackPane;

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
        todoNumLabel.setText("10");
        todoProgressLabel.setText("Progress: 100%");

        pastNumLabel.setText("20");
        pastProgressLabel.setText("Progress: 88%");

        importantNumLabel.setText("1");
        importantProgressLabel.setText("Progress: 10%");

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

        try{
            incomingView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("incoming-view.fxml")));
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
            todoListView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("todoList-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(todoListView);

        try{
            importantView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("important-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(importantView);

        try{
            settingsView = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("settings-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        innerStackPane.getChildren().add(settingsView);

        dashboardPane.toFront();
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
        System.out.println("Important Btn Clicked");
    }

    @FXML
    protected void onimportantBtnMouseEntered() {
        importantBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onsignoutBtnClicked() {
        refreshBtnStatus(signoutBtn);
        Platform.exit();
    }

    @FXML
    protected void onsignoutBtnMouseEntered() {
        signoutBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onsettingsBtnClicked() {
        settingsView.toFront();
        refreshBtnStatus(settingsBtn);
        System.out.println("Settings clicked!");
    }

    @FXML
    protected void onsettingsBtnMouseEntered() {
        settingsBtn.setCursor(Cursor.HAND);
    }
}
