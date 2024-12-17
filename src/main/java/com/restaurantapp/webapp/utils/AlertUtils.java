package com.restaurantapp.webapp.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertUtils {

    public static void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
