package com.ui.planner;

import com.planner.ToDoListsController;
import com.planner.User;
import com.planner.UserManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.datebase.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class AddTodoListController {
    @FXML
    private DatePicker whenDatePicker;
    @FXML
    private TextField eventTextField;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;

    private UserManager user; //done



    @FXML
    protected void onConfirmBtnClicked() {
        if (whenDatePicker.getValue() != null && eventTextField.getText().length() != 0) {

            // TODO: Store the user created to-do list task.
            LocalDate dateInput = whenDatePicker.getValue();
            String event = eventTextField.getText();
            System.out.println(dateInput);
            System.out.println(event);
//            JDBCSQlite jdbcsQlite = new JDBCSQlite();
//            jdbcsQlite.create();
//
//            try {
////                HashMap<LocalDate, String> todo = new HashMap<>();
////                todo.put(dateInput, event);
////                user.getToDoLists().addTaskInList(todo);
//                jdbcsQlite.createUserToDoListTaskByUserName(user.getName(), event, dateInput);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            ToDoListsController.newTask(user, event, dateInput);
            // To close the dialog.
            Stage stage = (Stage)cancelBtn.getScene().getWindow();
            stage.close();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fail to create this event");
            alert.setHeaderText(null);
            alert.setContentText("Fail to create this event, please check the date and event description.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onCancelBtnClicked() {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onCancelButtonMouseEntered() {
        cancelBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onConfirmBtnMouseEntered() {
        confirmBtn.setCursor(Cursor.HAND);
    }

    public void setUser(UserManager user) {
        this.user = user;
    }
}
