package com.restaurantapp.webapp.controllers.UserControllers;

import com.restaurantapp.webapp.models.Dish;
import com.restaurantapp.webapp.models.User;
import com.restaurantapp.webapp.models.UserRole;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class AddRowController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private ComboBox<UserRole> roleComboBox;
    @FXML
    private Button submitButton;

    @FXML
    private void submit() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        UserRole userRole = roleComboBox.getSelectionModel().getSelectedItem();
        try {
            if (Objects.isNull(username) || username.trim().isEmpty()
                    || Objects.isNull(password) || password.trim().isEmpty()
                    || Objects.isNull(userRole))
            {
                AlertUtils.showErrorMessage("All fields should be non-empty and contain correct values.");
            } else {
                User user = new User(username, password, userRole);
                APIService.createUser(user);
                restartFields();
            }
        } catch (NumberFormatException e) {
            AlertUtils.showErrorMessage("Invalid input data.");
        } catch (IOException | InterruptedException e) {
            AlertUtils.showErrorMessage("Error creating new user.");
        }
    }

    private void restartFields() {
        usernameField.clear();
        passwordField.clear();
    }

    public void initialize() throws IOException, InterruptedException {
        Callback<ListView<UserRole>, ListCell<UserRole>> cellFactory = lv -> new ListCell<UserRole>() {
            @Override
            protected void updateItem(UserRole item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };
        roleComboBox.setButtonCell(cellFactory.call(null));
        roleComboBox.setCellFactory(cellFactory);

        List<UserRole> items = APIService.loadUserRoles();
        for (UserRole item : items) {
            roleComboBox.getItems().add(item);
        }
    }

}
