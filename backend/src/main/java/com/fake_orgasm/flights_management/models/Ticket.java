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
    private String previousTicket;
    private String nextTicket;

    /**
     * This method constructs a Ticket object with the provided parameters.
     *
     * @param id       The ticket ID.
     * @param number   The ticket number.
     * @param priority The priority category of the ticket.
     * @param userId   The ID of the user associated with the ticket.
     * @param flightId The ID of the flight associated with the ticket.
     */
    public Ticket(String id, int number, Category priority,
                  int userId, String flightId) {
        this.id = id;
        this.number = number;
        this.priority = priority;
        this.userId = userId;
        this.flightId = flightId;
        this.previousTicket = "";
        this.nextTicket = "";
    }

    /**
     * This method constructs a Ticket object with the provided parameters.
     *
     * @param id             The ticket ID.
     * @param number         The ticket number.
     * @param priority       The priority category of the ticket.
     * @param userId         The ID of the user associated with the ticket.
     * @param flightId       The ID of the flight associated with the ticket.
     * @param previousTicket The ID of the previous ticket.
     * @param nextTicket     The ID of the next ticket.
     */
    public Ticket(String id, int number, Category priority,
                  int userId, String flightId, String previousTicket, String nextTicket) {
        this.id = id;
        this.number = number;
        this.priority = priority;
        this.userId = userId;
        this.flightId = flightId;
        this.previousTicket = previousTicket;
        this.nextTicket = nextTicket;
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
        Category priority = this.getPriority();
        Category priorityToCompare = ticket.getPriority();

        if (priority.getType().equals(priorityToCompare.getType())) {
            return Integer.compare(this.number, ticket.number);
        } else {
            return Integer.compare(priority.getNumber(),
                    priorityToCompare.getNumber());
        }
    }

    public boolean hasPrevious() {
        return !previousTicket.isEmpty();
    }

    public boolean hasNext() {
        return !nextTicket.isEmpty();
    }

    /**
     * This method returns a human-readable representation of the Ticket object.
     *
     * @return A string containing ticket details.
     */
    @Override
    public String toString() {
        return "\n(" + number + " - " + priority.getType() + ") - (id - " + id + ")" +
                "\nPrevious: " + previousTicket +
                "\nNext: " + nextTicket;
    }
}