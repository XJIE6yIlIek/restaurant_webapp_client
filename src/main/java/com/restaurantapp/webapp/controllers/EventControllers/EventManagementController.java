package com.restaurantapp.webapp.controllers.EventControllers;

import com.restaurantapp.webapp.RestaurantApplication;
import com.restaurantapp.webapp.models.Event;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.AlertUtils;
import com.restaurantapp.webapp.utils.CustomObjectConverter;
import com.restaurantapp.webapp.utils.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class EventManagementController {

    @FXML
    private TableView<Event> tableView;
    @FXML
    private TableColumn<Event, String> nameColumn, descriptionColumn;
    @FXML
    private TableColumn<Event, LocalDateTime> timeColumn;
    @FXML
    private TableColumn<Event, Long> idColumn, capacityColumn;
    @FXML
    private Button backButton, addRowButton;
    private ObservableList<Event> data;
    private Stack<Event> deletedRows = new Stack<>();

    @FXML
    private void handleGoBack() {
        StageManager.switchWindow((Stage) backButton.getScene().getWindow(), "manager.fxml", "Main");
    }

    @FXML
    private void handleAddRow() throws IOException {
        FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource("add_event.fxml"));
        Scene addRowScene = new Scene(loader.load());
        Stage addRowStage = new Stage();
        addRowStage.initOwner(addRowButton.getScene().getWindow());
        addRowStage.setTitle("Add event");
        addRowStage.setScene(addRowScene);
        addRowStage.show();
    }

    @FXML
    private void updateEventName(TableColumn.CellEditEvent<Event, String> event) throws IOException, InterruptedException {
        Event eventObj = event.getRowValue();
        eventObj.setName(event.getNewValue());
        APIService.updateEvent(eventObj);
    }

    @FXML
    private void updateEventDescription(TableColumn.CellEditEvent<Event, String> event) throws IOException, InterruptedException {
        Event eventObj = event.getRowValue();
        eventObj.setDescription(event.getNewValue());
        APIService.updateEvent(eventObj);
    }

    @FXML
    private void updateEventTime(TableColumn.CellEditEvent<Event, LocalDateTime> event) throws IOException, InterruptedException {
        Event eventObj = event.getRowValue();
        LocalDateTime newTime = event.getNewValue();
        if (!Objects.isNull(newTime)) {
            eventObj.setEventTime(newTime);
            APIService.updateEvent(eventObj);
        } else {
            tableView.refresh();
        }
    }

    @FXML
    private void updateEventCapacity(TableColumn.CellEditEvent<Event, Long> event) throws IOException, InterruptedException {
        Event eventObj = event.getRowValue();
        Long newCapacity = event.getNewValue();
        if (!Objects.isNull(newCapacity)) {
            eventObj.setCapacity(newCapacity);
            System.out.println(newCapacity);
            APIService.updateEvent(eventObj);
        } else {
            tableView.refresh();
        }
    }

    @FXML
    private void deleteRow() {
        Event selectedEvent = tableView.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(selectedEvent)) {
            try {
                APIService.deleteRow(selectedEvent.getId(), "events");
                data.remove(selectedEvent);
                deletedRows.push(selectedEvent);
            } catch (IOException | InterruptedException e) {
                AlertUtils.showErrorMessage("Something went wrong...");
            }
        }
    }

    @FXML
    private void undoDeleteRow() throws IOException, InterruptedException {
        if (!deletedRows.isEmpty()) {
            Event event = deletedRows.pop();
            data.add(event);
            APIService.createEvent(event);
        }
    }

    @FXML
    private void refreshTable() throws IOException, InterruptedException {
        loadData();
        AlertUtils.showInfoMessage("Table successfully refreshed.");
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("eventTime"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        capacityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new CustomObjectConverter.CustomLongStringConverter()));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    public void loadData() throws IOException, InterruptedException {
        data.clear();
        List<Event> events = APIService.loadEvents();
        data.addAll(events);
    }

}
