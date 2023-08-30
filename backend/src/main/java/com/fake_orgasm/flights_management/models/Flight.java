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
    private String sourceId;
    private String destinationId;
    private Date date;
    private int capacity;
    private PriorityQueue<Ticket> tickets;

    public Flight(String id, String sourceId, String destinationId,
                  Date date, int capacity) {
        this.id = id;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.date = date;
        this.capacity = capacity;
        this.tickets = new PriorityQueue<>();
    }

    public Flight(String id, String sourceId, String destinationId,
                  int capacity) {
        this.id = id;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.date = new Date();
        this.capacity = capacity;
        this.tickets = new PriorityQueue<>();
    }
}