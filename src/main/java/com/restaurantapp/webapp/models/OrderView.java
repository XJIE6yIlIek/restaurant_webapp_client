package com.restaurantapp.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Setter @Getter
@NoArgsConstructor
public class OrderView {

    private Long id;
    private String tableNumber;
    private String status;
    private LocalDateTime orderTime;
    private List<String> items;

    public OrderView(Long id, String tableNumber, OrderStatus status, LocalDateTime orderTime, List<OrderItem> items) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.status = status.getName();
        this.orderTime = orderTime;
        this.items = items.stream().map(item -> item
                .getQuantity() + " " + item.getDishId()).toList();
    }

}
