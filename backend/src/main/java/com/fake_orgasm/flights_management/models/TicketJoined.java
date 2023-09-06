package com.fake_orgasm.flights_management.models;

import lombok.Getter;

import java.util.Date;

@Getter
public class TicketJoined {

    private String id;
    private int userId;
    private String flightId;
    private int number;
    private Category priority;
    private Date date;
    private Airport source;
    private Airport destination;

    public TicketJoined(String id, int userId, String flightId, int number, Category priority,
                        Date date, Airport source, Airport destination) {
        this.id = id;
        this.userId = userId;
        this.flightId = flightId;
        this.number = number;
        this.priority = priority;
        this.date = date;
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "\n(id - " + id + ") - number: " + number + " - " + priority.getType() +
                "\nUser: " + userId +
                "\nFlight: " + flightId +
                "\nSource: " + source +
                "\nDestination: " + destination;
    }
}