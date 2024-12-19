package com.restaurantapp.webapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.restaurantapp.webapp.models.*;
import com.restaurantapp.webapp.utils.HttpUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class APIService {

    private static final String api_url = "http://localhost:8080/api";

    public static String login(String username, String password) throws IOException, InterruptedException {
        String url = api_url + "/login";
        LoginRequestPayload payload = new LoginRequestPayload(username, password);
        return HttpUtils.sendPostRequest(url, payload.toJson());
    }

    public static List<Dish> loadDishes() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/dishes";
        String response = HttpUtils.sendGetRequest(url);
        List<Dish> dishes = objectMapper.readValue(response, new TypeReference<>(){});
        return dishes;
    }

    public static List<OrderView> loadOrders() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/orders";
        String response = HttpUtils.sendGetRequest(url);
        List<Order> orders = objectMapper.readValue(response, new TypeReference<>(){});
        List<OrderView> orderViews = orders.stream().map(order -> new OrderView(order.getId(), order.getTableNumber(),
                order.getStatus(), order.getOrderTime(), order.getItems())).toList();
        return orderViews;
    }

    public static List<UserView> loadUsers() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/users";
        String response = HttpUtils.sendGetRequest(url);
        List<User> users = objectMapper.readValue(response, new TypeReference<>(){});
        List<UserView> userViews = users.stream().map(user -> new UserView(user.getId(), user.getUsername(),
                user.getRole().getName())).toList();
        return userViews;
    }

    public static List<UserRole> loadUserRoles() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String url = api_url + "/users/roles";
        String response = HttpUtils.sendGetRequest(url);
        List<UserRole> userRoles = objectMapper.readValue(response, new TypeReference<>(){});
        return userRoles;
    }

    public static void createDish(Dish dish) throws IOException, InterruptedException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String url = api_url + "/dishes/create";
        String response = HttpUtils.sendPostRequest(url, objectWriter.writeValueAsString(dish));
        System.out.println(response);
    }

    public static void updateDish(Dish dish) throws IOException, InterruptedException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String url = api_url + "/dishes/update/" + dish.getId();
        String response = HttpUtils.sendPostRequest(url, objectWriter.writeValueAsString(dish));
        System.out.println(response);
    }

    public static void deleteRow(Long id, String table) throws IOException, InterruptedException {
        String url = api_url + "/" + table + "/delete/" + id;
        String response = HttpUtils.sendDeleteRequest(url);
        System.out.println(response);
    }

    public static void updateOrderStatus(Order order) throws IOException, InterruptedException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String url = api_url + "/orders/update/" + order.getId();
        String response = HttpUtils.sendPostRequest(url, objectWriter.writeValueAsString(order));
        System.out.println(response);
    }

    public static void createOrder(Order order) throws IOException, InterruptedException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String url = api_url + "/orders/create";
        String response = HttpUtils.sendPostRequest(url, objectWriter.writeValueAsString(order));
        System.out.println(response);
    }

    public static void updateUser(User user) throws IOException, InterruptedException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String url = api_url + "/users/update/" + user.getId();
        String response = HttpUtils.sendPostRequest(url, objectWriter.writeValueAsString(user));
        System.out.println(response);
    }

    public static void createUser(User user) throws IOException, InterruptedException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String url = api_url + "/users/create";
        String response = HttpUtils.sendPostRequest(url, objectWriter.writeValueAsString(user));
        System.out.println(response);
    }

}
