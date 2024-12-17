package com.restaurantapp.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class LoginRequestPayload {

    private String username;
    private String password;

    public String toJson() {
        return String.format("{\"username\": \"%s\", \"password\": \"%s\"}", this.username, this.password);
    }

}
