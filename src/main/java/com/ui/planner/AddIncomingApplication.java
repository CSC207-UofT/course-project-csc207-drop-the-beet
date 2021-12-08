package com.ui.planner;

import com.planner.UseCases.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * add new window
 */
public class AddIncomingApplication extends Application {
    Stage stage = new Stage();
    private UserManager user;//done

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("addIncoming-view.fxml"));
        Parent root = fxmlLoader.load();
        AddInComingController addInComingController = fxmlLoader.getController();
        addInComingController.setUser(user);//done
        Scene scene = new Scene(root, 369, 268);
        stage.setTitle("Add Incoming List Task Demo");
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * set user
     * @param user from previous step
     */
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
