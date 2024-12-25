package com.restaurantapp.webapp.controllers.EventControllers;

import com.restaurantapp.webapp.models.Event;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class EventController {

    @FXML
    private TableView<Event> tableView;
    @FXML
    private TableColumn<Event, String> nameColumn, descriptionColumn;
    @FXML
    private TableColumn<Event, LocalDateTime> timeColumn;
    @FXML
    private TableColumn<Event, Long> capacityColumn;
    @FXML
    private Button backButton;
    private ObservableList<Event> data;

    @FXML
    private void handleGoBack() {
        backButton.setDisable(true);
        StageManager.switchWindow((Stage) backButton.getScene().getWindow(), "client.fxml", "Main");
        backButton.setDisable(false);
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("eventTime"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    public void loadData() throws IOException, InterruptedException {
        data.clear();
        List<Event> events = APIService.loadEvents();
        data.addAll(events);
    }

}
