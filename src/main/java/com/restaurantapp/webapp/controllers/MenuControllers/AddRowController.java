package com.restaurantapp.webapp.controllers.MenuControllers;

import com.restaurantapp.webapp.models.Dish;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class AddRowController {

    @FXML
    private TextField nameField, descriptionField, weightField, priceField;

    @FXML
    private void submit() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        try {
            float weight = Float.parseFloat(weightField.getText());
            float price = Float.parseFloat(priceField.getText());
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
