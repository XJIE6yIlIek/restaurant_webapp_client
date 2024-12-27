package com.restaurantapp.webapp.controllers.UserControllers;

import com.restaurantapp.webapp.RestaurantApplication;
import com.restaurantapp.webapp.models.User;
import com.restaurantapp.webapp.models.UserRole;
import com.restaurantapp.webapp.models.UserView;
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
    private Button backButton, addRowButton;
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
    private void updateUserUsername(TableColumn.CellEditEvent<UserView, String> event) throws  IOException, InterruptedException {
        UserView userView = event.getRowValue();
        User user = new User();
        String newUsername = event.getNewValue();
        if (!Objects.isNull(newUsername)) {
            user.setId(userView.getId());
            user.setUsername(newUsername);
            user.setRole(new UserRole(userView.getRole()));
            APIService.updateUser(user);
        } else {
            tableView.refresh();
        }
    }

    @FXML
    private void updateUserRole(TableColumn.CellEditEvent<UserView, String> event) throws IOException, InterruptedException {
        UserView userView = event.getRowValue();
        User user = new User();
        String newRole = event.getNewValue();
        if (!Objects.isNull(newRole)) {
            user.setId(userView.getId());
            user.setUsername(userView.getUsername());
            user.setRole(new UserRole(newRole));
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
