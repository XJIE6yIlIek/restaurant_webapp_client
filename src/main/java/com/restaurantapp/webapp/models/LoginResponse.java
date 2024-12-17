package com.restaurantapp.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class LoginResponse {

//    private String token;
    private String username;
    private String role;

}
