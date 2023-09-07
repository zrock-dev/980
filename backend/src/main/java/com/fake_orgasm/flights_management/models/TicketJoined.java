package com.fake_orgasm.flights_management.models;

import java.util.Date;
import lombok.Getter;

/**
 * This class represents a joined ticket entity containing
 * detailed information about a ticket, including its associated user,
 * flight, source, and destination airports.
 */
@Getter
public class TicketJoined {

    private String id;
    private String flightId;
    private int number;
    private Category priority;
    private Date date;
    private Airport source;
    private Airport destination;

    /**
     * This method constructs a new TicketJoined instance with the specified details.
     *
     * @param id          The unique identifier of the ticket.
     * @param flightId    The ID of the flight associated with the ticket.
     * @param number      The ticket number.
     * @param priority    The category or priority of the ticket.
     * @param date        The date of the ticket's arrival.
     * @param source      The source airport of the associated flight.
     * @param destination The destination airport of the associated flight.
     */
    public TicketJoined(
            String id, String flightId, int number, Category priority, Date date, Airport source, Airport destination) {
        this.id = id;
        this.flightId = flightId;
        this.number = number;
        this.priority = priority;
        this.date = date;
        this.source = source;
        this.destination = destination;
    }

    /**
     * This method returns a string representation of the TicketJoined object.
     *
     * @return A string representation containing ticket details, including ID,
     * number, priority, associated user, flight, source airport, and destination airport.
     */
    @Override
    public String toString() {
        return "\n(id - " + id + ") - number: " + number + " - " + priority.getType()
                + "\nFlight: " + flightId
                + "\nSource: " + source
                + "\nDestination: " + destination;
    }
}
