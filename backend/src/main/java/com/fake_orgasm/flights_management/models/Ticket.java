package com.fake_orgasm.flights_management.models;

import com.fake_orgasm.users_management.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket implements Comparable<Ticket>{

    private String id;
    private int number;
    private Category priority;
    private int userId;
    private String flightId;

    public Ticket(String id, int number, Category priority,
                  int userId, String flightId) {
        this.id = id;
        this.number = number;
        this.priority = priority;
        this.userId = userId;
        this.flightId = flightId;
    }

    @Override
    public int compareTo(Ticket ticket) {
        int priorityComparison = Integer.compare(
                this.priority.getNumber(), ticket.priority.getNumber());

        if (priorityComparison != 0) {
            return priorityComparison;
        } else {
            if (this.priority == Category.VIP ||
                    this.priority == Category.FREQUENT_PASSENGER) {
                return Integer.compare(this.number, ticket.number);
            } else {
                return Integer.compare(ticket.number, this.number);
            }
        }
    }

    @Override
    public String toString() {
        return "(" + priority.getType() + " - " + number + ") - " + flightId;
    }
}