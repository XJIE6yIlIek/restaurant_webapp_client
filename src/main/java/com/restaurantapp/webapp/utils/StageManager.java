package com.restaurantapp.webapp.utils;

import com.restaurantapp.webapp.RestaurantApplication;
import com.restaurantapp.webapp.controllers.MenuControllers.MenuController;
import com.restaurantapp.webapp.controllers.MenuControllers.MenuManagementController;
import com.restaurantapp.webapp.controllers.OrderControllers.OrderController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StageManager {

    public static void switchWindow(Stage stage, String fxmlFile, String sceneTitle) {
        try {
            Scene scene = new Scene(FXMLLoader.load(RestaurantApplication.class.getResource(fxmlFile)));
            stage.setScene(scene);
            stage.setTitle(sceneTitle);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to switch scene.");
            e.printStackTrace();
        }
    }

    public static void switchWindowToMenu(Stage stage, String fxmlFile, String sceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            MenuController controller = loader.getController();
            controller.loadData();
            stage.setScene(scene);
            stage.setTitle(sceneTitle);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to switch scene.");
            e.printStackTrace();
        }
    }

    public static void switchWindowToMenuManagement(Stage stage, String fxmlFile, String sceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            MenuManagementController controller = loader.getController();
            controller.loadData();
            stage.setScene(scene);
            stage.setTitle(sceneTitle);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to switch scene.");
            e.printStackTrace();
        }
    }

    public static void switchWindowToOrders(Stage stage, String fxmlFile, String sceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            OrderController controller = loader.getController();
            controller.loadData();
            stage.setScene(scene);
            stage.setTitle(sceneTitle);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to switch scene.");
            e.printStackTrace();
        }
    }

}
