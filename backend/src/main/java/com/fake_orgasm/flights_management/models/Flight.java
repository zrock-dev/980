package com.fake_orgasm.flights_management.models;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public final static int MIN_CAPACITY = 100;
    public final static int MAX_CAPACITY = 550;
    public final static int AVERAGE_CAPACITY =
            (MIN_CAPACITY + MAX_CAPACITY) / 2;

    public Flight(String id, String sourceId, String destinationId,
                  Date date, int capacity)
            throws FlightCapacityException {
        validateCapacity(capacity);
        this.id = id;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.date = date;
        this.capacity = capacity;
        this.ticketIds = "";
        this.tickets = new PriorityQueue<>();
    }

    public Flight(String id, String sourceId, String destinationId,
                  Date date, int capacity, String ticketIds)
            throws FlightCapacityException {
        validateCapacity(capacity);
        this.id = id;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.date = date;
        this.capacity = capacity;
        this.ticketIds = ticketIds;
        this.tickets = new PriorityQueue<>();
    }

    public Flight(String id, String sourceId, String destinationId,
                  int capacity)
            throws FlightCapacityException {
        validateCapacity(capacity);
        this.id = id;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.date = new Date();
        this.capacity = capacity;
        this.ticketIds = "";
        this.tickets = new PriorityQueue<>();
    }

    private void validateCapacity(int capacity) throws FlightCapacityException {
        if (capacity < MIN_CAPACITY || capacity > MAX_CAPACITY) {
            throw new FlightCapacityException();
        }
    }

    public boolean isAvailable() {
        return tickets.size() < capacity || ticketIds.split(",").length < capacity;
    }

    public void addTicket(Ticket ticket) {
        if (isAvailable()) {
            tickets.add(ticket);
        }
    }

    public void addTicket(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            addTicket(ticket);
        }
    }

    public List<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        while (!this.tickets.isEmpty()) {
            tickets.add(this.tickets.remove());
        }

        return tickets;
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
        return numberOfTickets() + 1;
    }

    public int numberOfTickets() {
        if (tickets.isEmpty() && ticketIds.isEmpty()) {
            return 0;
        } else if (tickets.isEmpty()) {
            return ticketIds.split(",").length;
        } else {
            return tickets.size();
        }
    }

    public Ticket getNextTicket() {
        return tickets.remove();
    }

    public boolean existNextTicket() {
        return tickets.peek() != null;
    }

    @Override
    public String toString() {
        return "(" + numberOfTickets() + " - Tickets ) - " + id + "\n(" + sourceId + " - " + destinationId + ") - " + date.toString();
    }
}