package com.fake_orgasm.flights_management.models;

import lombok.Getter;
import lombok.Setter;

/**
 * This class has the responsibility of representing a flight ticket and its properties.
 */
@Getter
@Setter
public class Ticket implements Comparable<Ticket> {

    private String id;
    private int number;
    private Category priority;
    private int userId;
    private String flightId;

    /**
     * This method constructs a Ticket object with the provided parameters.
     *
     * @param id       The ticket ID.
     * @param number   The ticket number.
     * @param priority The priority category of the ticket.
     * @param userId   The ID of the user associated with the ticket.
     * @param flightId The ID of the flight associated with the ticket.
     */
    public Ticket(String id, int number, Category priority, int userId, String flightId) {
        this.id = id;
        this.number = number;
        this.priority = priority;
        this.userId = userId;
        this.flightId = flightId;
    }

    /**
     * This method compares this ticket to another ticket based on their priority and number.
     *
     * @param ticket The ticket to compare to.
     * @return A negative integer, zero, or a positive integer as this ticket
     * is less than, equal to, or greater than the specified ticket.
     */
    @Override
    public int compareTo(Ticket ticket) {
        int priorityComparison = Integer.compare(this.priority.getNumber(), ticket.priority.getNumber());

        if (priorityComparison != 0) {
            return priorityComparison;
        } else {
            if (this.priority == Category.VIP || this.priority == Category.FREQUENT_PASSENGER) {
                return Integer.compare(this.number, ticket.number);
            } else {
                return Integer.compare(ticket.number, this.number);
            }
        }
    }

    /**
     * This method returns a human-readable representation of the Ticket object.
     *
     * @return A string containing ticket details.
     */
    @Override
    public String toString() {
        return "(" + priority.getType() + " - " + number + ") - " + flightId;
    }
}
