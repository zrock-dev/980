package com.fake_orgasm.flights_management.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.PriorityQueue;

/**
 * This is the flight history class.
 */
@Getter
@Setter
public class Flight {

    private String id;
    private Airport source;
    private Airport destination;
    private Date date;
    private int capacity;
    private PriorityQueue<Ticket> tickets;

    public Flight(String id, Airport source, Airport destination,
                  Date date, int capacity) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.capacity = capacity;
        this.tickets = new PriorityQueue<>();
    }

    public Flight(String id, Airport source, Airport destination,
                  int capacity) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = new Date();
        this.capacity = capacity;
        this.tickets = new PriorityQueue<>();
    }
}