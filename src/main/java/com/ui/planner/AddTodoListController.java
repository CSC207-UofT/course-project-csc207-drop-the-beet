package com.ui.planner;

import com.planner.User;
import javafx.application.Platform;
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

public class AddTodoListController {
    @FXML
    private DatePicker whenDatePicker;
    @FXML
    private TextField eventTextField;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;

    private User user;

    @FXML
    protected void onConfirmBtnClicked() {
        if (whenDatePicker.getValue() != null && eventTextField.getText().length() != 0) {
            JDBCSQlite jdbcsQlite = new JDBCSQlite();
            jdbcsQlite.create();

            // TODO: Store the user created to-do list task.
            LocalDate dateInput = whenDatePicker.getValue();
            String event = eventTextField.getText();
            System.out.println(dateInput);
            System.out.println(event);
            try {
                jdbcsQlite.createUserToDoListTaskByUserName(user.getName(), event, dateInput);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public void setUser(User user) {
        this.user = user;
    }
}
