package com.restaurantapp.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    private Long id;
    private String name;

    public UserRole(String name) {
        this.name = name;
    }
}
