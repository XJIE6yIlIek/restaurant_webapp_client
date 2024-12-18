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
        tableButton.setDisable(true);
//        StageManager.switchWindowToUsers((Stage) tableButton.getScene().getWindow(), "user_table.fxml", "Users"); TODO: add user_table.fxml
        tableButton.setDisable(false);
    }

    @FXML
    private void handleLogout() {
        logoutButton.setDisable(true);
        StageManager.switchWindow((Stage) logoutButton.getScene().getWindow(), "login.fxml", "Login");
        logoutButton.setDisable(false);
    }

}
