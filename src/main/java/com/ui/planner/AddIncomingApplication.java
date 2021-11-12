package com.ui.planner;

import com.planner.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddIncomingApplication extends Application {
    Stage stage = new Stage();
    private UserManager user;//done

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("addIncoming-view.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        AddInComingController addInComingController = (AddInComingController) fxmlLoader.getController();
        addInComingController.setUser(user);//done
        Scene scene = new Scene(root, 369, 268);
        stage.setTitle("Add Incoming List Task Demo");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void setUser(UserManager user) {
        this.user = user;
    }//done

    public void showWindow() throws IOException{
        start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
