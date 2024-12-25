package com.restaurantapp.webapp.controllers.MainPageControllers;

import com.restaurantapp.webapp.utils.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManagerController {

    @FXML
    private Button dishMenuButton, eventMenuButton;
    @FXML
    private Button logoutButton;

    @FXML
    private void handleDishMenuOpen() {
        StageManager.switchWindowToMenuManagement((Stage) dishMenuButton.getScene().getWindow(), "dish_table_management.fxml", "Menu management");
    }

    @FXML
    private void handleEventMenuOpen() {
        StageManager.switchWindowToEventManagementMenu((Stage) eventMenuButton.getScene().getWindow(), "event_table_management.fxml", "Events management");
    }

    @FXML
    private void handleLogout() {
        StageManager.switchWindow((Stage) logoutButton.getScene().getWindow(), "login.fxml", "Login");
    }

}
