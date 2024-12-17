package com.restaurantapp.webapp.controllers;

import com.restaurantapp.webapp.utils.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import static com.restaurantapp.webapp.services.APIService.login;
import static com.restaurantapp.webapp.utils.AlertUtils.showErrorMessage;

public class LoginController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    private void handleLogin() throws IOException, InterruptedException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showErrorMessage("Either username or password is blank.");
            return;
        }

        loginButton.setDisable(true);

        String response = login(username, password);

        if (!Objects.isNull(response) && response.contains("role")) {
            JSONObject jsonObject = new JSONObject(response);
            String userRole = jsonObject.getString("role").toLowerCase();
            String fxmlFile = "";
            System.out.println();
            switch (userRole) {
                case "client":
                    fxmlFile = "client.fxml";
                    break;
                case "employee":
                    fxmlFile = "employee.fxml";
                    break;
                case "manager":
                    fxmlFile = "manager.fxml";
                    break;
                case "admin":
                    fxmlFile = "admin.fxml";
                    break;
                default:
                    showErrorMessage("Unknown user role.");
                    return;
            }
            StageManager.switchWindow((Stage) loginButton.getScene().getWindow(), fxmlFile);
        } else {
            showErrorMessage("Authentication failed.");
        }
        loginButton.setDisable(false);
    }

}