package com.ui.planner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class DashboardView extends Application {
    Stage stage = new Stage();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("dashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 842, 521);
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
