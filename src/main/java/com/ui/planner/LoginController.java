package com.ui.planner;

import com.database.*;
import com.planner.Gateway.UserGateway;
import com.planner.UseCases.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
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
//    @FXML
//    private Pane bgPane;

    private final JDBCSQlite jdbcsQlite = new JDBCSQlite();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jdbcsQlite.create();
    }

    /**
     * @Description: This is a GUI interface to handle the event when the login button is clicked.
     * @Param: void
     * @Return: void
     */
    @FXML
    protected void onLoginButtonClick() throws IOException, SQLException {
        String userName = userNameText.getText();
        String passWord = passWordText.getText();

        UserManager user = UserGateway.loadAllUserInfo(userName);
        System.out.println("xx" + user);
        assert user != null;
        if (user.getPassword().equals(passWord)) {
            passwordSuccessView(user);
        }
        else {
            passwordFailedView();
        }//Todo 57 64
    }

    /**
     * @Description: This is a GUI interface to handle the event when the remember me check box is selected.
     * @Param: void
     * @Return: void
     */
    @FXML
    protected void onRememberMeChecked() {
        // TODO: Write a function to read a file stored to determined whether remember me should be selected
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
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    protected void passwordSuccessView(UserManager user) throws IOException {
        DashboardView dashboard = new DashboardView(user);
        dashboard.showWindow();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        jdbcsQlite.close();
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