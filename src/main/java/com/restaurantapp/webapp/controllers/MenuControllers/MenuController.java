package com.restaurantapp.webapp.controllers.MenuControllers;

import com.restaurantapp.webapp.models.Dish;
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
import java.util.List;

public class MenuController {

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

    @FXML
    private void handleGoBack() {
        backButton.setDisable(true);
        StageManager.switchWindow((Stage) backButton.getScene().getWindow(), "client.fxml", "Main");
        backButton.setDisable(false);
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    public void loadData() throws IOException, InterruptedException {
        data.clear();
        List<Dish> dishes = APIService.loadDishes();
        data.addAll(dishes);
    }

}
