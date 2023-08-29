package com.fake_orgasm.flights_management.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {

    private String id;
    private int number;
    private Category priority;
    private String userId;
    private String flightId;

    public Ticket(String id, int number, Category priority,
                  String userId, String flightId) {
        this.id = id;
        this.number = number;
        this.priority = priority;
        this.userId = userId;
        this.flightId = flightId;
    }
}