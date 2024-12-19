package com.restaurantapp.webapp.controllers.UserControllers;

import com.restaurantapp.webapp.RestaurantApplication;
import com.restaurantapp.webapp.models.Dish;
import com.restaurantapp.webapp.models.User;
import com.restaurantapp.webapp.models.UserRole;
import com.restaurantapp.webapp.models.UserView;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class UserController {

    @FXML
    private TableView<UserView> tableView;
    @FXML
    private TableColumn<UserView, Long> idColumn;
    @FXML
    private TableColumn<UserView, String> usernameColumn, roleColumn;
    @FXML
    private Button backButton, refreshButton, addRowButton;
    private ObservableList<UserView> data;
    private Stack<UserView> deletedRows = new Stack<>();

    @FXML
    private void handleGoBack() {
        StageManager.switchWindow((Stage) backButton.getScene().getWindow(), "admin.fxml", "Main");
    }

    @FXML
    private void handleAddRow() throws IOException {
        FXMLLoader loader = new FXMLLoader(RestaurantApplication.class.getResource("add_user.fxml"));
        Scene addRowScene = new Scene(loader.load());
        Stage addRowStage = new Stage();
        addRowStage.initOwner(addRowButton.getScene().getWindow());
        addRowStage.setTitle("Add user");
        addRowStage.setScene(addRowScene);
        addRowStage.show();
    }

    @FXML
    private void updateUserUsername(TableColumn.CellEditEvent<User, String> event) throws  IOException, InterruptedException { // FIXME: checkbox doesn't have commit by default, make it happen...with magic, idk
        User user = event.getRowValue();
        String newUsername = event.getNewValue();
        if (!Objects.isNull(newUsername)) {
            user.setUsername(newUsername);
            APIService.updateUser(user);
        } else {
            tableView.refresh();
        }
    }

    @FXML
    private void updateUserRole(TableColumn.CellEditEvent<User, String> event) throws IOException, InterruptedException {
        User user = event.getRowValue();
        String newRole = event.getNewValue();
        UserRole newUserRole = new UserRole();
        newUserRole.setName(newRole);
        if (!Objects.isNull(newRole)) {
            user.setRole(newUserRole);
            APIService.updateUser(user);
        } else {
            tableView.refresh();
        }
    }

    @FXML
    private void deleteRow() {
        UserView selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (!Objects.isNull(selectedUser)) {
            try {
                APIService.deleteRow(selectedUser.getId(), "users");
                data.remove(selectedUser);
                deletedRows.push(selectedUser);
            } catch (IOException | InterruptedException e) {
                AlertUtils.showErrorMessage("Something went wrong...");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void refreshTable() throws IOException, InterruptedException {
        loadData();
        AlertUtils.showInfoMessage("Table successfully refreshed.");
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        usernameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }

    public void loadData() throws IOException, InterruptedException {
        data.clear();
        List<UserView> users = APIService.loadUsers();
        data.addAll(users);
    }

}
