package com.ui.ui;

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

public class HelloController implements Initializable {
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
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(System.getProperty("user.dir"));
    }

    @FXML
    protected void onUserNameTyped() {
        if (userNameText.getLength() != 0 && passWordText.getLength() != 0) {
            loginButton.setDisable(false);
        } else {
            loginButton.setDisable(true);
        }

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

            passWordText.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode() == KeyCode.ENTER && userNameText.getLength() != 0 && passWordText.getLength() != 0) {
                        try {
                            onLoginButtonClick();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }

    /**
     * @Description: This is a GUI interface to handle the event when the login button is clicked.
     * @Param: void
     * @Return: void
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        LoginInfo loginInfo = new LoginInfo(userNameText.getText(), passWordText.getText());
        String userName = loginInfo.getUserName();
        String passWord = loginInfo.getPassWord();

        // To handle the username and password the user entered.
        if (userName.equals("Drop") && passWord.equals("thebeets")) {
            // The password and username is successful.
            com.ui.ui.DashboardApplication dashboard = new com.ui.ui.DashboardApplication();
            dashboard.showWindow();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }

        else {
            // The password and username is unsuccessful.
            unableToLoginLabel.setVisible(true);
            userNameText.clear();
            passWordText.clear();
            userNameText.setPromptText("Username");
            passWordText.setPromptText("Password");
            loginButton.setDisable(true);
            userNameText.requestFocus();
        }
    }

    @FXML
    protected void onUserNameMouseClicked() {
        unableToLoginLabel.setVisible(false);
    }

    /**
     * @Description: This is a GUI interface to handle the event when the remember me check box is selected.
     * @Param: void
     * @Return: void
     */
    @FXML
    protected void onRememberMeChecked() {
        // To read json file and fill the text field.
        if (rememberMe.isSelected()) {
            userNameText.setText("Elin");
        }
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
    protected void onSignUpClicked() {
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