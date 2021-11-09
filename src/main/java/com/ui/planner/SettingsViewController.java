package com.ui.planner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsViewController implements Initializable {
    @FXML
    private TextField userNameText;
    @FXML
    private TextField emailText;
    @FXML
    private PasswordField newPasswordText;
    @FXML
    private PasswordField confirmPasswordText;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userNameText.setText("ElinLengLeng");
        emailText.setText("Lengleng@lengleng.com");
    }

    @FXML
    protected void onConfirmButtonClicked() {
        if (newPasswordText.equals(confirmPasswordText)) {
            // TODO: Change the user's information in the database
            String newUserName = userNameText.getText();
            String newEmail = emailText.getText();
            String newPassword = newPasswordText.getText();

        } else {
            // if two password not match
            // TODO: Reset the UserName and email.
            userNameText.setText("Elin");
            emailText.setText("lengleng@lengleng.com");

            // No need to change code under this line
            newPasswordText.clear();
            confirmPasswordText.clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fail to change password");
            alert.setHeaderText(null);
            alert.setContentText("Fail to change, the new password and confirm password not match.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onConfirmBtnMouseEntered() {
        confirmBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onCancelBtnClicked() {
        // TODO: If the user cancel the change, set the username, email to the original one.
        userNameText.setText("Elin");
        emailText.setText("lengleng@lengleng.com");
    }

    @FXML
    protected void onCancelBtnMouseEntered() {
        cancelBtn.setCursor(Cursor.HAND);
    }

}
