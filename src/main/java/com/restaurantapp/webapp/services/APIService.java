package com.restaurantapp.webapp.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.webapp.models.*;
import com.restaurantapp.webapp.utils.AlertUtils;
import com.restaurantapp.webapp.utils.HttpUtils;

import java.io.IOException;
import java.util.List;

public class APIService {

    private static final String basic_url = "http://localhost:8080";
    private static final String api_url = basic_url + "/api";

    public static String login(String username, String password) throws IOException, InterruptedException {
        String url = api_url + "/login";
        LoginRequestPayload payload = new LoginRequestPayload(username, password);
        String response = HttpUtils.sendPostRequest(url, payload.toJson());
        return response;
    }

    public static List<Dish> loadDishes() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/dishes";
        String response = HttpUtils.sendGetRequest(url);
        return objectMapper.readValue(response, new TypeReference<>(){});
    }

    public static List<OrderView> loadOrders() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/orders";
        String response = HttpUtils.sendGetRequest(url);
        List<Order> orders = objectMapper.readValue(response, new TypeReference<>(){});
        return orders.stream().map(order -> new OrderView(order.getId(), order.getTableNumber(),
                order.getStatus(), order.getOrderTime(), order.getItems()))
                .toList();
    }

    public static List<UserView> loadUsers() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/users";
        String response = HttpUtils.sendGetRequest(url);
        List<User> users = objectMapper.readValue(response, new TypeReference<>(){});
        return users.stream().map(user -> new UserView(user.getId(), user.getUsername(),
                user.getRole().getName())).toList();
    }

    public static List<UserRole> loadUserRoles() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/users/roles";
        String response = HttpUtils.sendGetRequest(url);
        return objectMapper.readValue(response, new TypeReference<>(){});
    }

    public static List<Event> loadEvents() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/events";
        String response = HttpUtils.sendGetRequest(url);
        return objectMapper.readValue(response, new TypeReference<>(){});
    }

    public static void createDish(Dish dish) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/dishes/create";
        HttpUtils.sendPostRequest(url, objectMapper.writeValueAsString(dish));
        AlertUtils.showInfoMessage("Dish created successfully.");
    }

    public static void updateDish(Dish dish) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/dishes/update/" + dish.getId();
        HttpUtils.sendPostRequest(url, objectMapper.writeValueAsString(dish));
        AlertUtils.showInfoMessage("Dish updated successfully.");
    }

    public static void deleteRow(Long id, String table) throws IOException, InterruptedException {
        String url = api_url + "/" + table + "/delete/" + id;
        HttpUtils.sendDeleteRequest(url);
        AlertUtils.showInfoMessage("Row deleted successfully.");
    }

    public static void updateOrderStatus(Order order) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/orders/update/" + order.getId();
        HttpUtils.sendPostRequest(url, objectMapper.writeValueAsString(order));
        AlertUtils.showInfoMessage("Order status updated successfully.");
    }

    public static void createOrder(Order order) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/orders/create";
        HttpUtils.sendPostRequest(url, objectMapper.writeValueAsString(order));
        AlertUtils.showInfoMessage("Order created successfully.");
    }

    public static void updateUser(User user) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/users/update/" + user.getId();
        HttpUtils.sendPostRequest(url, objectMapper.writeValueAsString(user));
        AlertUtils.showInfoMessage("User updated successfully.");
    }

    public static void createUser(User user) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/users/create";
        HttpUtils.sendPostRequest(url, objectMapper.writeValueAsString(user));
        AlertUtils.showInfoMessage("User created successfully.");
    }

    public static void createEvent(Event event) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/events/create";
        HttpUtils.sendPostRequest(url, objectMapper.writeValueAsString(event));
        AlertUtils.showInfoMessage("Event created successfully.");
    }

    public static void updateEvent(Event event) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/events/update/" + event.getId();
        HttpUtils.sendPostRequest(url, objectMapper.writeValueAsString(event));
        AlertUtils.showInfoMessage("Event updated successfully.");
    }

}
