package com.restaurantapp.webapp;

import com.restaurantapp.webapp.utils.AlertUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class RestaurantApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(RestaurantApplication.class.getResource("login.fxml"))));
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to load application.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}