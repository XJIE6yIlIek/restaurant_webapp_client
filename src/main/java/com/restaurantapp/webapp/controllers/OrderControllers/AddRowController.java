package com.restaurantapp.webapp.controllers.OrderControllers;

import com.restaurantapp.webapp.models.Dish;
import com.restaurantapp.webapp.models.Order;
import com.restaurantapp.webapp.models.OrderItem;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.AlertUtils;
import com.restaurantapp.webapp.utils.CustomObjectConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class AddRowController {

    @FXML
    private TextField tableField;
    @FXML
    private TableView<OrderItem> tableView;
    @FXML
    private TableColumn<OrderItem, Long> idColumn, nameColumn, quantityColumn;
    @FXML
    private ComboBox<Dish> dishComboBox;
    private ObservableList<OrderItem> data = FXCollections.observableArrayList();
    private Stack<OrderItem> deletedRows = new Stack<>();

    public void initialize() throws IOException, InterruptedException {
        Callback<ListView<Dish>, ListCell<Dish>> cellFactory = lv -> new ListCell<>() {

            @Override
            protected void updateItem(Dish item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };
        tableView.setItems(data);
        dishComboBox.setButtonCell(cellFactory.call(null));
        dishComboBox.setCellFactory(cellFactory);

        List<Dish> items = APIService.loadDishes();
        for (Dish item : items) {
            dishComboBox.getItems().add(item);
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<>("dishId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new CustomObjectConverter.CustomLongStringConverter()));
    }

    @FXML
    private void submit() throws IOException, InterruptedException {
        String table = tableField.getText();
        if (Objects.isNull(table) || table.trim().isEmpty()
            || data.isEmpty())
        {
            AlertUtils.showErrorMessage("All fields should be non-empty and contain correct values.");
        } else {
            Order order = new Order();
            order.setTableNumber(table);
            order.setItems(data);
            APIService.createOrder(order);
            restartFields();
        }
    }

    @FXML
    private void updateQuantity(TableColumn.CellEditEvent<OrderItem, Long> event) {
        OrderItem orderItem = event.getRowValue();
        Long newQuantity = event.getNewValue();
        if (!Objects.isNull(newQuantity) && newQuantity > 0L) {
            orderItem.setQuantity(newQuantity);
            tableView.refresh();
        }
    }

    @FXML
    private void addItem() {
        Dish dish = dishComboBox.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(dish)) {
            OrderItem orderItem = new OrderItem();
            orderItem.setDishId(dish.getId());
            orderItem.setDishName(dish.getName());
            orderItem.setQuantity(1L);
            data.add(orderItem);
            tableView.refresh();
        } else {
            AlertUtils.showErrorMessage("Select a dish before adding it to order.");
        }
    }

    @FXML
    private void deleteRow() {
        OrderItem selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(selectedItem)) {
            tableView.getItems().remove(selectedItem);
            deletedRows.add(selectedItem);
        }
    }

    @FXML
    private void undoDeleteRow() {
        if (!deletedRows.isEmpty()) {
            tableView.getItems().add(deletedRows.pop());
        }
    }

    private void restartFields() {
        tableField.clear();
        tableView.getItems().clear();
    }

}
