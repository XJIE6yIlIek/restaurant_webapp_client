package com.restaurantapp.webapp.controllers.MenuControllers;

import com.restaurantapp.webapp.models.Dish;
import com.restaurantapp.webapp.services.APIService;
import com.restaurantapp.webapp.utils.AlertUtils;
import com.restaurantapp.webapp.utils.ObjectConverter;
import com.restaurantapp.webapp.utils.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
    private Button backButton;
    private ObservableList<Dish> data;
    private ObjectConverter.LongStringConverter longStringConverter;
    private ObjectConverter.FloatStringConverter floatStringConverter;


    @FXML
    private void handleGoBack() {
        backButton.setDisable(true);
        StageManager.switchWindow((Stage) backButton.getScene().getWindow(), "client.fxml", "Main");
        backButton.setDisable(false);
    }

    @FXML
    private void addDish() {
        Dish newDish = new Dish(null, "", "", null, null);
        data.add(newDish);
        tableView.getSelectionModel().selectLast();
        tableView.edit(data.size() - 1, nameColumn);
    }

    @FXML
    private void saveNewDish() throws IOException, InterruptedException {
        Dish newDish = data.get(data.size() - 1);
        if (newDish.getName().isEmpty() || newDish.getDescription().isEmpty()
                || Objects.isNull(newDish.getWeight()) || newDish.getWeight() <= 0
                || Objects.isNull(newDish.getPrice()) || newDish.getPrice() <= 0) {
            AlertUtils.showErrorMessage("Every column has to be not empty.\nNumeric values should be positive.");
        } else {
            APIService.createDish(newDish);
            loadData();
        }
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
    private void updateDishWeight(TableColumn.CellEditEvent<Dish, String> event) throws IOException, InterruptedException {
        Dish dish = event.getRowValue();
        dish.setWeight(Float.valueOf(event.getNewValue()));
        APIService.updateDish(dish);
    }

    @FXML
    private void updateDishPrice(TableColumn.CellEditEvent<Dish, String> event) throws IOException, InterruptedException {
        Dish dish = event.getRowValue();
        dish.setPrice(Float.valueOf(event.getNewValue()));
        APIService.updateDish(dish);
    }

    @FXML
    private void deleteDish() {
        Dish selectedDish = tableView.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(selectedDish)) {
            try {
                APIService.deleteRow(selectedDish.getId(), "dishes");
                data.remove(selectedDish);
            } catch (IOException | InterruptedException e) {
                AlertUtils.showErrorMessage("Something went wrong...");
                e.printStackTrace();
            }
        }
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(longStringConverter));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn(floatStringConverter));
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(floatStringConverter));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    public void loadData() throws IOException, InterruptedException {
        data.clear();
        List<Dish> dishes = APIService.loadDishes();
        data.addAll(dishes);
    }

}
