package com.restaurantapp.webapp.controllers.MainPageControllers;

import com.restaurantapp.webapp.utils.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClientController {

    @FXML
    private Button dishMenuButton, eventMenuButton;
    @FXML
    private Button logoutButton;

    @FXML
    private void handleDishMenuOpen() {
        StageManager.switchWindowToDishMenu((Stage) dishMenuButton.getScene().getWindow(), "dish_table.fxml", "Dish menu");
    }

    @FXML
    private void handleEventMenuOpen() {
        StageManager.switchWindowToEventMenu((Stage) eventMenuButton.getScene().getWindow(), "event_table.fxml", "Event table");
    }

    @FXML
    private void handleLogout() {
        StageManager.switchWindow((Stage) logoutButton.getScene().getWindow(), "login.fxml", "Login");
    }

}
