package com.ui.planner;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class DashboardView extends Application {
    Stage stage = new Stage();

    private final String userName;
    private final String userEmail;
    private final String userPassword;

    public DashboardView(String userName, String userEmail, String userPassword){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("dashboard-view.fxml"));
        Parent root = (Parent)fxmlLoader.load();

        DashboardController controller = fxmlLoader.<DashboardController>getController();
        controller.setUser(userName, userEmail, userPassword);

        Scene scene = new Scene(root, 842, 521);
        stage.setTitle("Drop The Beets Group Planner Demo");
        stage.setScene(scene);
        stage.show();
    }


    public void showWindow() throws IOException{
        start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
