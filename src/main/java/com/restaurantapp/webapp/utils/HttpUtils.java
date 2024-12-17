package com.restaurantapp.webapp.utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtils {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String sendPostRequest(String url, String payload) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public static String sendGetRequest(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public static String sendDeleteRequest(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .DELETE()
                .build();
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

}
