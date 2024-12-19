package com.restaurantapp.webapp.controllers.MenuControllers;

import com.restaurantapp.webapp.models.Dish;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Stack;

public class AddRowController {

    @FXML
    private TextField nameField, descriptionField, weightField, priceField;
    @FXML
    private Button submitButton;

    @FXML
    private void submit() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        try {
            Float weight = Float.parseFloat(weightField.getText());
            Float price = Float.parseFloat(priceField.getText());
            if (Objects.isNull(name) || name.trim().isEmpty()
                || Objects.isNull(description) || description.trim().isEmpty()
                || weight <= 0
                || price <= 0)
            {
                AlertUtils.showErrorMessage("All fields should be non-empty and contain correct values.");
            } else {
                Dish dish = new Dish(name, description, weight, price);
                APIService.createDish(dish);
                restartFields();
            }
        } catch (NumberFormatException e) {
            AlertUtils.showErrorMessage("Invalid input data.");
        } catch (IOException | InterruptedException e) {
            AlertUtils.showErrorMessage("Error creating new dish.");
        }
    }

    private void restartFields() {
        nameField.clear();
        descriptionField.clear();
        weightField.clear();
        priceField.clear();
    }

}
