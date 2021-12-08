package com.ui.planner;

import com.planner.Gateway.UserGateway;
import com.planner.UseCases.UserManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * dashboard page
 */
public class DashboardView extends Application {
    Stage stage = new Stage();

    private final UserManager user;

    /**
     * initialize dashboard view
     * @param user this user
     */
    public DashboardView(UserManager user){
        this.user = user;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("dashboard-view.fxml"));
        Parent root = fxmlLoader.load();

        DashboardController controller = fxmlLoader.getController();
        controller.setUser(user); //done

        Scene scene = new Scene(root, 842, 521);
        stage.setTitle("Drop The Beets Group Planner Demo");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("The windows is exiting...");
            UserGateway.writeAllUserInfo(user);
        });
    }


    public void showWindow() throws IOException{
        start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
