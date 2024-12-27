package com.restaurantapp.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private Long id;
    private Long dishId;
    private String dishName;
    private Long quantity;

}
