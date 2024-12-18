package com.restaurantapp.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

    private Long id;
    private String name;
    private String description;
    private Float weight;
    private Float price;

}
