package com.ui.planner;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private Label forgotPassword;
    @FXML
    private Label signUp;
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField passWordText;
    @FXML
    private Label unableToLoginLabel;
    @FXML
    private CheckBox rememberMe;
    @FXML
    private Button loginButton;
    @FXML
    private Pane bgPane;

    /**
     * @Description: This is a GUI interface to handle the event when the login button is clicked.
     * @Param: void
     * @Return: void
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        String userName = userNameText.getText();
        String passWord = passWordText.getText();

        // TODO: Change the if condition to a function that compares the password
        if (userName.equals("Drop") && passWord.equals("thebeets")) {
            passwordSuccessView();
        }
        else {
            passwordFailedView();
        }
    }

    /**
     * @Description: This is a GUI interface to handle the event when the remember me check box is selected.
     * @Param: void
     * @Return: void
     */
    @FXML
    protected void onRememberMeChecked() {
        // TODO: Write a function to read a file stored to determined whether remember me should be slected
        //  or not.
        rememberMe.setSelected(true);

        // TODO: Write a function to read username from a local file to fill the Username textile.
        if (rememberMe.isSelected()) {
            userNameText.setText("Elin");
        }
    }

    @FXML
    protected void onUserNameTyped() {
        loginButton.setDisable(userNameText.getLength() == 0 || passWordText.getLength() == 0);

        if (userNameText.getLength() != 0) {
            unableToLoginLabel.setVisible(false);
        }
    }

    @FXML
    protected void onPassWordTyped() {
        if (userNameText.getLength() != 0 && passWordText.getLength() != 0) {
            loginButton.setDisable(false);
        } else {
            loginButton.setDisable(true);

            passWordText.setOnKeyPressed(event -> {
                if(event.getCode() == KeyCode.ENTER && userNameText.getLength() != 0 && passWordText.getLength() != 0) {
                    try {
                        onLoginButtonClick();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    protected void passwordSuccessView() throws IOException {
        DashboardView dashboard = new DashboardView();
        dashboard.showWindow();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void passwordFailedView() {
        // The password and username is unsuccessful.
        unableToLoginLabel.setVisible(true);
        userNameText.clear();
        passWordText.clear();
        userNameText.setPromptText("Username");
        passWordText.setPromptText("Password");
        loginButton.setDisable(true);
        userNameText.requestFocus();
    }

    @FXML
    protected void onUserNameMouseClicked() {
        unableToLoginLabel.setVisible(false);
    }

    @FXML
    protected void onRememberMeEntered() {
        rememberMe.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onForgotPasswordMouseEntered() {
        forgotPassword.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onForgotPasswordClicked() {
        System.out.println("Forgot Password Clicked!");
    }

    @FXML
    protected void onSignUpClicked() throws IOException {
        SignupView signupView = new SignupView();
        signupView.showWindow();
        System.out.println("Sign up Click!");
    }

    @FXML
    protected void onSignUpEntered() {
        signUp.setCursor(Cursor.HAND);
    }

    @FXML
    protected void onloginBtnMouseEntered() {
        loginButton.setCursor(Cursor.HAND);
    }

}