package com.restaurantapp.webapp.controllers.MenuControllers;

import com.restaurantapp.webapp.RestaurantApplication;
import com.restaurantapp.webapp.models.Dish;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.AlertUtils;
import com.restaurantapp.webapp.utils.CustomObjectConverter;
import com.restaurantapp.webapp.utils.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class MenuManagementController {

    @FXML
    private TableView<Dish> tableView;
    @FXML
    private TableColumn<Dish, Long> idColumn;
    @FXML
    private TableColumn<Dish, String> nameColumn;
    @FXML
    private TableColumn<Dish, String> descriptionColumn;
    @FXML
    private TableColumn<Dish, Float> weightColumn;
    @FXML
    private TableColumn<Dish, Float> priceColumn;
    @FXML
    private Button backButton, addRowButton;
    private ObservableList<Dish> data;
    private Stack<Dish> deletedRows = new Stack<>();


    @FXML
    private void handleGoBack() {
        backButton.setDisable(true);
        StageManager.switchWindow((Stage) backButton.getScene().getWindow(), "manager.fxml", "Main");
        backButton.setDisable(false);
    }

    @FXML
    private void handleAddRow() throws IOException {
        FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource("add_dish.fxml"));
        Scene addRowScene = new Scene(loader.load());
        Stage addRowStage = new Stage();
        addRowStage.initOwner(addRowButton.getScene().getWindow());
        addRowStage.setTitle("Add dish");
        addRowStage.setScene(addRowScene);
        addRowStage.show();
    }

    @FXML
    private void updateDishName(TableColumn.CellEditEvent<Dish, String> event) throws IOException, InterruptedException {
        Dish dish = event.getRowValue();
        dish.setName(event.getNewValue());
        APIService.updateDish(dish);
    }

    @FXML
    private void updateDishDescription(TableColumn.CellEditEvent<Dish, String> event) throws IOException, InterruptedException {
        Dish dish = event.getRowValue();
        dish.setDescription(event.getNewValue());
        APIService.updateDish(dish);
    }

    @FXML
    private void updateDishWeight(TableColumn.CellEditEvent<Dish, Float> event) throws IOException, InterruptedException {
        Dish dish = event.getRowValue();
        Float newWeight = event.getNewValue();
        if (!Objects.isNull(newWeight)) {
            dish.setPrice(newWeight);
            APIService.updateDish(dish);
        } else {
            tableView.refresh();
        }
    }

    @FXML
    private void updateDishPrice(TableColumn.CellEditEvent<Dish, Float> event) throws IOException, InterruptedException {
        Dish dish = event.getRowValue();
        Float newPrice = event.getNewValue();
        if (!Objects.isNull(newPrice)) {
            dish.setPrice(newPrice);
            APIService.updateDish(dish);
        } else {
            tableView.refresh();
        }
    }

    @FXML
    private void deleteRow() {
        Dish selectedDish = tableView.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(selectedDish)) {
            try {
                APIService.deleteRow(selectedDish.getId(), "dishes");
                data.remove(selectedDish);
                deletedRows.push(selectedDish);
            } catch (IOException | InterruptedException e) {
                AlertUtils.showErrorMessage("Something went wrong...");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void undoDeleteRow() throws IOException, InterruptedException {
        if (!deletedRows.isEmpty()) {
            Dish dish = deletedRows.pop();
            data.add(dish);
            APIService.createDish(dish);
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
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new CustomObjectConverter.CustomFloatStringConverter()));
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new CustomObjectConverter.CustomFloatStringConverter()));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    public void loadData() throws IOException, InterruptedException {
        data.clear();
        List<Dish> dishes = APIService.loadDishes();
        data.addAll(dishes);
    }

}
