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
        if (newPasswordText.getText().equals(confirmPasswordText.getText()) && newPasswordText.getLength() != 0 && confirmPasswordText.getLength() != 0) {
            // TODO: Change the user's information in the database
            String newUserName = userNameText.getText();
            String newEmail = emailText.getText();
            String newPassword = newPasswordText.getText();
            System.out.println(newUserName);
            System.out.println(newEmail);
            System.out.println(newPassword);

        } else {
            // if two password not match
            // TODO: Reset the UserName and email.
            userNameText.setText("ElinLengLeng");
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
    protected void onResetBtnClicked() {
        // TODO: If the user cancel the change, set the username, email to the original one.
        userNameText.setText("ElinLengLeng");
        emailText.setText("lengleng@lengleng.com");

        // No need to change code under this line.
        newPasswordText.clear();
        confirmPasswordText.clear();
    }

    @FXML
    protected void onResetBtnMouseEntered() {
        cancelBtn.setCursor(Cursor.HAND);
    }

}
