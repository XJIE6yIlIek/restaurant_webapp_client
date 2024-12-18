package com.restaurantapp.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @JsonIgnore
    private Long id;
    private Long dishId;
    private Long quantity;

}
