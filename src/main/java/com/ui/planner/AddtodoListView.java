package com.ui.planner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AddtodoListView extends Application {
    Stage stage = new Stage();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("addTodoList-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 369, 268);
        stage.setTitle("Add To-do List Task Demo");
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
