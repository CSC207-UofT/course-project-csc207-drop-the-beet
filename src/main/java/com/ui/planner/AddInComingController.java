package com.ui.planner;

import com.planner.UseCases.UserManager;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AddInComingController {
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endingDatePicker;
    @FXML
    private TextField eventTextField;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;

    private UserManager user;//done

    /**
     * receive the inputs and add new schedule
     */
    @FXML
    protected void onConfirmBtnClicked() {
        if (startDatePicker.getValue() != null && endingDatePicker.getValue() != null && eventTextField.getText().length() != 0) {
            // TODO: Store the user created schedule.
            LocalDate startDateInput = startDatePicker.getValue();
            LocalDate endingDateInput = endingDatePicker.getValue();
            String event = eventTextField.getText();
            System.out.println("Starting date: " + startDateInput);
            System.out.println("Ending date: " + endingDateInput);
            System.out.println(event);
            user.getSchedules().addSchedule(startDateInput, endingDateInput, event);//done

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

    /**
     * when cancel button is clicked
     */
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

    /**
     * set up user
     * @param user from previous step
     */
    public void setUser(UserManager user) {
        this.user = user;
    }
}
