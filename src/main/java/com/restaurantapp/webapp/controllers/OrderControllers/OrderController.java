package com.restaurantapp.webapp.controllers.OrderControllers;

import com.restaurantapp.webapp.models.OrderView;
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

public class OrderController {

    @FXML
    private TableView<OrderView> tableView;
    @FXML
    private TableColumn<OrderView, String> tableColumn;
    @FXML
    private TableColumn<OrderView, String> statusColumn;
    @FXML
    private TableColumn<OrderView, LocalDateTime> timeColumn;
    @FXML
    private TableColumn<OrderView, Float> itemsColumn;
    @FXML
    private Button backButton;
    private ObservableList<OrderView> data;

    @FXML
    private void handleGoBack() {
        backButton.setDisable(true);
        StageManager.switchWindow((Stage) backButton.getScene().getWindow(), "client.fxml", "Main");
        backButton.setDisable(false);
    }

    public void initialize() {
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        itemsColumn.setCellValueFactory(new PropertyValueFactory<>("items"));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    public void loadData() throws IOException, InterruptedException {
        data.clear();
        List<OrderView> orders = APIService.loadOrders();
        data.addAll(orders);
    }

}
