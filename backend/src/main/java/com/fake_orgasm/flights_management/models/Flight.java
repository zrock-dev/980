package com.fake_orgasm.flights_management.models;

import com.fake_orgasm.flights_management.exceptions.FlightCapacityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import lombok.Getter;
import lombok.Setter;

/**
 * This class has the responsibility of representing flight information and managing
 * flight-related operations.
 */
@Getter
@Setter
public class Flight {

    public static final int MIN_CAPACITY = 100;
    public static final int MAX_CAPACITY = 550;
    public static final int AVERAGE_CAPACITY = (MIN_CAPACITY + MAX_CAPACITY) / 2;
    private String id;
    private String sourceId;
    private String destinationId;
    private Date date;
    private int capacity;
    private String ticketIds;
    private PriorityQueue<Ticket> tickets;

    /**
     * This constructor creates a Flight object with the provided parameters.
     * It also validates the flight capacity to ensure it falls within the acceptable range.
     *
     * @param id            The flight ID.
     * @param sourceId      The source airport ID.
     * @param destinationId The destination airport ID.
     * @param date          The date of the flight.
     * @param capacity      The capacity of the flight.
     * @throws FlightCapacityException If the capacity is outside the acceptable range.
     */
    public Flight(String id, String sourceId, String destinationId, Date date, int capacity)
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

    /**
     * This constructor creates a Flight object with the provided parameters, including
     * ticket IDs.
     * It also validates the flight capacity to ensure it falls within the acceptable range.
     *
     * @param id            The flight ID.
     * @param sourceId      The source airport ID.
     * @param destinationId The destination airport ID.
     * @param date          The date of the flight.
     * @param capacity      The capacity of the flight.
     * @param ticketIds     The comma-separated string of ticket IDs.
     * @throws FlightCapacityException If the capacity is outside the acceptable range.
     */
    public Flight(String id, String sourceId, String destinationId, Date date, int capacity, String ticketIds)
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

    /**
     * This constructor creates a Flight object with the provided parameters, including
     * ticket IDs.
     * It also validates the flight capacity to ensure it falls within the acceptable range.
     *
     * @param id            The flight ID.
     * @param sourceId      The source airport ID.
     * @param destinationId The destination airport ID.
     * @param capacity      The capacity of the flight.
     * @throws FlightCapacityException If the capacity is outside the acceptable range.
     */
    public Flight(String id, String sourceId, String destinationId, int capacity) throws FlightCapacityException {
        validateCapacity(capacity);
        this.id = id;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.date = new Date();
        this.capacity = capacity;
        this.ticketIds = "";
        this.tickets = new PriorityQueue<>();
    }

    /**
     * This method validates that the given flight capacity falls within the acceptable range.
     *
     * @param capacity The flight capacity to validate.
     * @throws FlightCapacityException If the capacity is outside the acceptable range.
     */
    private void validateCapacity(int capacity) throws FlightCapacityException {
        if (capacity < MIN_CAPACITY || capacity > MAX_CAPACITY) {
            throw new FlightCapacityException();
        }
    }

    /**
     * This method checks if there are available seats on the flight.
     *
     * @return True if there are available seats, otherwise false.
     */
    public boolean isAvailable() {
        return tickets.size() < capacity || ticketIds.split(",").length < capacity;
    }

    /**
     * This method adds a ticket to the flight if there are available seats.
     *
     * @param ticket The ticket to add.
     */
    public void addTicket(Ticket ticket) {
        if (isAvailable()) {
            tickets.add(ticket);
        }
    }

    /**
     * This method adds a list of tickets to the flight if there are available seats.
     *
     * @param tickets The list of tickets to add.
     */
    public void addTicket(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            addTicket(ticket);
        }
    }

    /**
     * This method retrieves a list of tickets from the flight's priority queue.
     *
     * @return A list of tickets.
     */
    public List<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        while (!this.tickets.isEmpty()) {
            tickets.add(this.tickets.remove());
        }

        return tickets;
    }

    /**
     * This method adds a ticket ID to the comma-separated string of ticket IDs.
     *
     * @param ticketId The ticket ID to add.
     */
    public void addTicketId(String ticketId) {
        ticketIds += ticketId + ",";
    }

    /**
     * This method retrieves a string containing priority ticket IDs from the flight's priority queue.
     * The ticket IDs are concatenated into a comma-separated string.
     *
     * @return A comma-separated string of priority ticket IDs.
     */
    public String getPriorityTickets() {
        while (!tickets.isEmpty()) {
            addTicketId(tickets.remove().getId());
        }

        return ticketIds;
    }

    /**
     * This method gets the next available ticket number.
     *
     * @return The next available ticket number.
     */
    public int getNextNumber() {
        return numberOfTickets() + 1;
    }

    /**
     * This method calculates and returns the total number of tickets on the flight,
     * considering both the priority queue and the comma-separated string of ticket IDs.
     *
     * @return The total number of tickets on the flight.
     */
    public int numberOfTickets() {
        if (tickets.isEmpty() && ticketIds.isEmpty()) {
            return 0;
        } else if (tickets.isEmpty()) {
            return ticketIds.split(",").length;
        } else {
            return tickets.size();
        }
    }

    /**
     * This method retrieves and removes the next ticket from the priority queue.
     *
     * @return The next ticket in the priority queue.
     */
    public Ticket getNextTicket() {
        return tickets.remove();
    }

    /**
     * This method checks if there is a next ticket available in the priority queue.
     *
     * @return True if there is a next ticket, otherwise false.
     */
    public boolean existNextTicket() {
        return tickets.peek() != null;
    }

    /**
     * this method returns a human-readable representation of the Flight object.
     *
     * @return A string containing flight details.
     */
    @Override
    public String toString() {
        return "(" + numberOfTickets() + " - Tickets ) - " + id + "\n(" + sourceId + " - " + destinationId + ") - "
                + date.toString();
    }
}
