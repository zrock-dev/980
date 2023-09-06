package com.fake_orgasm.flights_management.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Represents a joined flight entity containing detailed information about a flight,
 * including its source and destination airports, date, capacity,
 * availability, and associated tickets.
 */
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

    /**
     * This method constructs a new FlightJoined instance with the specified details.
     *
     * @param id The unique identifier of the flight.
     * @param source The source airport of the flight.
     * @param destination The destination airport of the flight.
     * @param date The date of the flight's departure.
     * @param capacity The maximum capacity of the flight.
     * @param ticketIds A comma-separated string of ticket IDs associated with the flight.
     */
    public FlightJoined(String id, Airport source, Airport destination, Date date,
                        int capacity, String ticketIds) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.capacity = capacity;
        this.ticketIds = ticketIds;
    }

    /**
     * This method checks if the flight is available for booking
     * based on its current occupancy.
     *
     * @return True if the flight has available seats, false otherwise.
     */
    public boolean isAvailable() {
        return ticketIds.split(",").length < capacity;
    }

    /**
     * This method returns a string representation of the FlightJoined object.
     *
     * @return A string representation containing flight details, including ID, date,
     * capacity, availability, source airport, and destination airport.
     */
    @Override
    public String toString() {
        return "\n(id - " + id + ") - " + date + " - " + capacity + " - " + isAvailable() +
                "\nSource: " + source +
                "\nDestination: " + destination;
    }
}