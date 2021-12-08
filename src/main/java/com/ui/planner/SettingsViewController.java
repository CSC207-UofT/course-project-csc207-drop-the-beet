package com.ui.planner;

import com.planner.UseCases.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.database.*;
import java.net.URL;
import java.sql.SQLException;
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

    private String userName;
    private String userEmail;


    public void setUser(UserManager user) {
        userName = user.getName();
        userEmail = user.getEmail();
        user.getPassword();
        userNameText.setText(userName);
        emailText.setText(userEmail);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void onConfirmButtonClicked() throws SQLException {
        if (newPasswordText.getText().equals(confirmPasswordText.getText()) && newPasswordText.getLength() != 0 && confirmPasswordText.getLength() != 0) {
            DBUser jdbcsQlite = new DBUser();
            jdbcsQlite.create();
            String newUserName = userNameText.getText();
            String newEmail = emailText.getText();
            String newPassword = newPasswordText.getText();
            userEmail = emailText.getText();

            jdbcsQlite.changeUserEmailByUserName(newUserName, newEmail);
            jdbcsQlite.changeUserPasswordByUserName(newUserName, newPassword);
            jdbcsQlite.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Change User Info Successfully!");
            alert.setHeaderText(null);
            alert.setContentText("Change User Info Successfully!");
            alert.showAndWait();

            emailText.setText(userEmail);
            newPasswordText.clear();
            confirmPasswordText.clear();

        } else {
            // if two password not match
            emailText.setText(userEmail);

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
        userNameText.setText(userName);
        emailText.setText(userEmail);

        // No need to change code under this line.
        newPasswordText.clear();
        confirmPasswordText.clear();
    }

    @FXML
    protected void onResetBtnMouseEntered() {
        cancelBtn.setCursor(Cursor.HAND);
    }

}
