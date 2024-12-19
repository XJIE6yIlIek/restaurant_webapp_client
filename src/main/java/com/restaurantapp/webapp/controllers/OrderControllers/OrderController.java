package com.restaurantapp.webapp.controllers.OrderControllers;

import com.restaurantapp.webapp.RestaurantApplication;
import com.restaurantapp.webapp.models.Dish;
import com.restaurantapp.webapp.models.Order;
import com.restaurantapp.webapp.models.OrderStatus;
import com.restaurantapp.webapp.models.OrderView;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.AlertUtils;
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
    private Button backButton, refreshButton, addRowButton;
    private ObservableList<OrderView> data;

    @FXML
    private void updateOrderStatus(TableColumn.CellEditEvent<OrderView, String> event) throws IOException, InterruptedException {
        OrderView orderView = event.getRowValue();
        Order order = new Order();
        order.setId(orderView.getId());
        OrderStatus newOrderStatus = new OrderStatus();
        newOrderStatus.setName(event.getNewValue());
        order.setStatus(newOrderStatus);
        APIService.updateOrderStatus(order);
    }

    @FXML
    private void handleAddRow() throws IOException {
        FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource("add_order.fxml"));
        Scene addRowScene = new Scene(loader.load());
        Stage addRowStage = new Stage();
        addRowStage.initOwner(addRowButton.getScene().getWindow());
        addRowStage.setTitle("Add order");
        addRowStage.setScene(addRowScene);
        addRowStage.show();
    }

    @FXML
    private void handleGoBack() {
        backButton.setDisable(true);
        StageManager.switchWindow((Stage) backButton.getScene().getWindow(), "employee.fxml", "Main");
        backButton.setDisable(false);
    }

    @FXML
    private void refreshTable() throws IOException, InterruptedException {
        loadData();
        AlertUtils.showInfoMessage("Table successfully refreshed.");
    }

    public void initialize() {
        tableColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
        itemsColumn.setCellValueFactory(new PropertyValueFactory<>("items"));

        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    public void loadData() throws IOException, InterruptedException {
        data.clear();
        List<OrderView> orders = APIService.loadOrders();
        data.addAll(orders);
    }

}
