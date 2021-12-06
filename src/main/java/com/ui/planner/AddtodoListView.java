package com.ui.planner;

import com.planner.UseCases.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AddtodoListView extends Application {
    Stage stage = new Stage();

    private UserManager user;

    public void setUser(UserManager user) {
        this.user = user;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("addTodoList-view.fxml"));
        Parent root = fxmlLoader.load();
        AddTodoListController addTodoListController  = fxmlLoader.getController();
        addTodoListController.setUser(user);
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
