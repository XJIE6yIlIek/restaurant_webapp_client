package com.restaurantapp.webapp.utils;

import com.restaurantapp.webapp.RestaurantApplication;
import com.restaurantapp.webapp.controllers.EventControllers.EventController;
import com.restaurantapp.webapp.controllers.EventControllers.EventManagementController;
import com.restaurantapp.webapp.controllers.MenuControllers.MenuController;
import com.restaurantapp.webapp.controllers.MenuControllers.MenuManagementController;
import com.restaurantapp.webapp.controllers.OrderControllers.OrderController;
import com.restaurantapp.webapp.controllers.UserControllers.UserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class StageManager {

    public static void switchWindow(Stage stage, String fxmlFile, String sceneTitle) {
        try {
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(RestaurantApplication.class.getResource(fxmlFile))));
            stage.setScene(scene);
            stage.setTitle(sceneTitle);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to switch scene.");
        }
    }

    public static void switchWindowToDishMenu(Stage stage, String fxmlFile, String sceneTitle) {
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
        }
    }

    public static void switchWindowToEventMenu(Stage stage, String fxmlFile, String sceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            EventController controller = loader.getController();
            controller.loadData();
            stage.setScene(scene);
            stage.setTitle(sceneTitle);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to switch scene.");
        }
    }

    public static void switchWindowToEventManagementMenu(Stage stage, String fxmlFile, String sceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            EventManagementController controller = loader.getController();
            controller.loadData();
            stage.setScene(scene);
            stage.setTitle(sceneTitle);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to switch scene.");
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
        }
    }

    public static void switchWindowToUsers(Stage stage, String fxmlFile, String sceneTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            UserController controller = loader.getController();
            controller.loadData();
            stage.setScene(scene);
            stage.setTitle(sceneTitle);
            stage.show();
        } catch (Exception e) {
            AlertUtils.showErrorMessage("Failed to switch scene.");
        }
    }

}
