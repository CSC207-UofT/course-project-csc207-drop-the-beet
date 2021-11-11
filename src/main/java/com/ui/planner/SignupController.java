package com.ui.planner;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.datebase.*;

import java.io.IOException;
import java.sql.SQLException;

public class SignupController {
    @FXML
    private TextField userNameText;
    @FXML
    private TextField emailText;
    @FXML
    private PasswordField passWordText;
    @FXML
    private PasswordField confirmPasswordText;
    @FXML
    private Button signupBtn;
    @FXML
    private Button backToLoginBtn;

    @FXML
    protected void onConfirmPasswordTyped() {
        if (userNameText.getLength() != 0 && emailText.getLength() != 0 && passWordText.getLength() != 0 && confirmPasswordText.getLength() != 0) {
            signupBtn.setDisable(false);
        } else {
            signupBtn.setDisable(true);

            confirmPasswordText.setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER && userNameText.getLength() != 0 && passWordText.getLength() != 0) {
                    try {
                        onSignUpBtnClicked();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    protected void onSignUpBtnClicked() throws SQLException {
        JDBCSQlite jdbcsQlite = new JDBCSQlite();
        jdbcsQlite.create();

        if (passWordText.getText().equals(confirmPasswordText.getText()) && !jdbcsQlite.isUserNameExist(userNameText.getText())) {
            jdbcsQlite.createNewUser(userNameText.getText(), emailText.getText(), passWordText.getText());
            signUpSuccessHandler();
            jdbcsQlite.close();

        } else {
            // No need to Change the code under this line
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("password not match");
            alert.setHeaderText(null);
            alert.setContentText("Fail to sign up, the password not match.");
            alert.showAndWait();
            signUpFailureHandler();
            jdbcsQlite.close();
        }
    }

    @FXML
    protected void onBackToLoginBtnClicked() {
        // Close the stage
        Stage stage = (Stage)backToLoginBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void signUpFailureHandler() {
        userNameText.clear();
        emailText.clear();
        passWordText.clear();
        confirmPasswordText.clear();
        userNameText.requestFocus();
        signupBtn.setDisable(true);
    }

    @FXML
    protected void signUpSuccessHandler() {
        userNameText.clear();
        emailText.clear();
        passWordText.clear();
        confirmPasswordText.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up Successfully");
        alert.setHeaderText(null);
        alert.setContentText("Thank you! Sign Up Successfully.");
        alert.showAndWait();
    }

    @FXML
    protected void onSignUpBtnMouseEntered() {
        signupBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onBackToLoginBtnMouseEntered() {
        backToLoginBtn.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onUserNameTyped() {
        signupBtn.setDisable(userNameText.getLength() == 0 || emailText.getLength() == 0 || passWordText.getLength() == 0 || confirmPasswordText.getLength() == 0);
    }

    @FXML
    protected void onEmailTyped() {
        signupBtn.setDisable(userNameText.getLength() == 0 || emailText.getLength() == 0 || passWordText.getLength() == 0 || confirmPasswordText.getLength() == 0);
    }

    @FXML
    protected void onPasswordTyped() {
        signupBtn.setDisable(userNameText.getLength() == 0 || emailText.getLength() == 0 || passWordText.getLength() == 0 || confirmPasswordText.getLength() == 0);
    }
}
