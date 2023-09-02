package com.fake_orgasm.flights_management.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    private String ticketIds;
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
                  Date date, int capacity, String ticketIds) {
        this.id = id;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.date = date;
        this.capacity = capacity;
        this.ticketIds = ticketIds;
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

    public boolean isAvailable() {
        return tickets.size() < capacity || ticketIds.split(",").length < capacity;
    }

    public void addTicket(Ticket ticket) {
        if (isAvailable()) {
            tickets.add(ticket);
        }
    }

    public void addTicket(Ticket... tickets) {
        for (Ticket ticket : tickets) {
            addTicket(ticket);
        }
    }

    public void addTicketId(String ticketId) {
        ticketIds += ticketId + ",";
    }

    public String getPriorityTickets() {
        while (!tickets.isEmpty()) {
            addTicketId(tickets.remove().getId());
        }

        return ticketIds;
    }

    public int getNextNumber() {
        return tickets.isEmpty()
                ? ticketIds.split(",").length + 1
                : tickets.size() + 1;
    }

    @Override
    public String toString() {
        return "(" + sourceId + " - " + destinationId + ") - " + date.toString();
    }
}