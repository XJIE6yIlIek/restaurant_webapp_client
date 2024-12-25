package com.restaurantapp.webapp.controllers.EventControllers;

import com.restaurantapp.webapp.models.Dish;
import com.restaurantapp.webapp.models.Event;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.AlertUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AddRowController {

    @FXML
    private TextField nameField, descriptionField, capacityField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button submitButton;
    @FXML
    private ComboBox<Integer> hourComboBox, minuteComboBox;

    @FXML
    private void submit() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        LocalDate eventDate = datePicker.getValue();
        Integer selectedHour = hourComboBox.getSelectionModel().getSelectedItem();
        Integer selectedMinute = minuteComboBox.getSelectionModel().getSelectedItem();
        try {
            Long capacity = Long.parseLong(capacityField.getText());
            if (Objects.isNull(name) || name.trim().isEmpty()
                    || Objects.isNull(description) || description.trim().isEmpty()
                    || capacity <= 0
                    || Objects.isNull(selectedHour)
                    || Objects.isNull(selectedMinute))
            {
                AlertUtils.showErrorMessage("All fields should be non-empty and contain correct values.");
            } else {
                LocalDateTime eventTime = LocalDateTime.of(eventDate, LocalTime.of(selectedHour, selectedMinute));
                Event event = new Event(name, description, eventTime, capacity);
                APIService.createEvent(event);
                restartFields();
            }
        } catch (NumberFormatException e) {
            AlertUtils.showErrorMessage("Invalid input data.");
        } catch (IOException | InterruptedException e) {
            AlertUtils.showErrorMessage("Error creating new dish.");
        }
    }

    public void initialize() {
        for (int hour = 0; hour < 24; hour++) {
            hourComboBox.getItems().add(hour);
        }
        for (int minute = 0; minute < 60; minute++) {
            minuteComboBox.getItems().add(minute);
        }
    }

    private void restartFields() {
        nameField.clear();
        descriptionField.clear();
        capacityField.clear();
    }

}
