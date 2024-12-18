package com.restaurantapp.webapp.controllers.MainPageControllers;

import com.restaurantapp.webapp.utils.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EmployeeController {

    @FXML
    private Button tableButton;
    @FXML
    private Button logoutButton;

    @FXML
    private void handleTableOpen() {
        tableButton.setDisable(true);
        StageManager.switchWindowToOrders((Stage) tableButton.getScene().getWindow(), "order_table.fxml", "Orders");
        tableButton.setDisable(false);
    }

    @FXML
    private void handleLogout() {
        logoutButton.setDisable(true);
        StageManager.switchWindow((Stage) logoutButton.getScene().getWindow(), "login.fxml", "Login");
        logoutButton.setDisable(false);
    }

}
