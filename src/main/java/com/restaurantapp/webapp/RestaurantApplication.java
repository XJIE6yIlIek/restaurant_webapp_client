package com.restaurantapp.webapp;

import com.restaurantapp.webapp.utils.AlertUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RestaurantApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Scene scene = new Scene(FXMLLoader.load(RestaurantApplication.class.getResource("login_test.fxml")));
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to load application.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}