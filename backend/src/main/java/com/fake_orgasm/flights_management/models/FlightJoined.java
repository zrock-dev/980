package com.fake_orgasm.flights_management.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FlightJoined {

    private String id;
    private Airport source;
    private Airport destination;
    private Date date;
    private int capacity;
    private boolean isAvailable;
    private String ticketIds;
    private List<Ticket> tickets;

    public FlightJoined(String id, Airport source, Airport destination, Date date,
                        int capacity, String ticketIds) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.capacity = capacity;
        this.ticketIds = ticketIds;
    }

    public boolean isAvailable() {
        return ticketIds.split(",").length < capacity;
    }

    @Override
    public String toString() {
        return "\n(id - " + id + ") - " + date + " - " + capacity + " - " + isAvailable() +
                "\nSource: " + source +
                "\nDestination: " + destination;
    }
}