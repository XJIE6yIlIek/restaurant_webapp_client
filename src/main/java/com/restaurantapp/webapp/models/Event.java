package com.restaurantapp.webapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventTime;
    private Long capacity;

    public Event(String name, String description, LocalDateTime eventTime, Long capacity) {
        this.name = name;
        this.description = description;
        this.eventTime = eventTime;
        this.capacity = capacity;
    }

}
