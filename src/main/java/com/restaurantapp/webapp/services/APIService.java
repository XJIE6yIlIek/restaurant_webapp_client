package com.restaurantapp.webapp.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurantapp.webapp.models.LoginRequestPayload;
import com.restaurantapp.webapp.models.LoginResponse;
import com.restaurantapp.webapp.utils.HttpUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class APIService {

    private static final String api_url = "http://localhost:8080/api";

    public static String login(String username, String password) throws IOException, InterruptedException {
        String url = api_url + "/login";
        LoginRequestPayload payload = new LoginRequestPayload(username, password);
        return HttpUtils.sendPostRequest(url, payload.toJson());
    }

}
