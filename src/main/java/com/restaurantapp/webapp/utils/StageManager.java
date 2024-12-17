package com.restaurantapp.webapp.utils;

import com.restaurantapp.webapp.RestaurantApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StageManager {

    public static void switchWindow(Stage stage, String fxmlFile) {
        try {
            Scene scene = new Scene(FXMLLoader.load(RestaurantApplication.class.getResource(fxmlFile)));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to switch scene.");
            e.printStackTrace();
        }
    }

}
