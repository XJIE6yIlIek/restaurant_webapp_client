module com.restaurantapp.webapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires static lombok;
    requires org.json;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.restaurantapp.webapp to javafx.fxml;
    exports com.restaurantapp.webapp;
    exports com.restaurantapp.webapp.controllers;
    opens com.restaurantapp.webapp.controllers to javafx.fxml;
    exports com.restaurantapp.webapp.utils;
    opens com.restaurantapp.webapp.utils to javafx.fxml;
}