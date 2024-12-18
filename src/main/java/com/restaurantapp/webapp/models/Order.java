package com.restaurantapp.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @JsonIgnore
    private Long id;
    private String tableNumber;
    private OrderStatus status;
    private LocalDateTime orderTime;
    private List<OrderItem> items;

}
