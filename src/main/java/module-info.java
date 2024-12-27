module com.restaurantapp.webapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires static lombok;
    requires org.json;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.prefs;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.restaurantapp.webapp to javafx.fxml;
    exports com.restaurantapp.webapp;
    exports com.restaurantapp.webapp.utils;
    opens com.restaurantapp.webapp.utils to javafx.fxml;
    exports com.restaurantapp.webapp.models to com.fasterxml.jackson.databind;
    opens com.restaurantapp.webapp.models to javafx.base;
    exports com.restaurantapp.webapp.controllers.MenuControllers;
    opens com.restaurantapp.webapp.controllers.MenuControllers to javafx.fxml;
    exports com.restaurantapp.webapp.controllers.OrderControllers;
    opens com.restaurantapp.webapp.controllers.OrderControllers to javafx.fxml;
    exports com.restaurantapp.webapp.controllers.MainPageControllers;
    opens com.restaurantapp.webapp.controllers.MainPageControllers to javafx.fxml;
    exports com.restaurantapp.webapp.controllers.UserControllers;
    opens com.restaurantapp.webapp.controllers.UserControllers to javafx.fxml;
    exports com.restaurantapp.webapp.controllers.EventControllers;
    opens com.restaurantapp.webapp.controllers.EventControllers to javafx.fxml;
}