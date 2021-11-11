package com.ui.planner;

import com.planner.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddImportantView extends Application {
    Stage stage = new Stage();
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("addImportantList-view.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        AddImportantController addImportantController = (AddImportantController) fxmlLoader.getController();
        addImportantController.setUser(user);
        Scene scene = new Scene(root, 369, 268);
        stage.setTitle("Add To-do List Task Demo");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void showWindow() throws IOException{
        start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
