package com.restaurantapp.webapp.controllers.MainPageControllers;

import com.restaurantapp.webapp.utils.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private Button tableButton;
    @FXML
    private Button logoutButton;

    @FXML
    private void handleTableOpen() {
        StageManager.switchWindowToUsers((Stage) tableButton.getScene().getWindow(), "user_table.fxml", "Users");
    }

    @FXML
    private void handleLogout() {
        StageManager.switchWindow((Stage) logoutButton.getScene().getWindow(), "login.fxml", "Login");
    }

}
