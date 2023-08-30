package com.fake_orgasm.generator.flight_history_generator;

import com.fake_orgasm.users_management.models.Category;
import lombok.Getter;
import lombok.Setter;

/**
 *This is the flight history class.
 *
 */
@Getter
@Setter
public class FlightHistory {
    private Airport departureAirport;
    private Airport destinationAirport;
    private Category ticketType;

    /**
     * This is the constructor for this class.
     *
     * @param departureAirport   The departure airport for the flight history.
     * @param destinationAirport The destination airport for the flight history.
     * @param priority         The ticket priority for the flight history.
     */
    public FlightHistory(Airport departureAirport, Airport destinationAirport, Category priority) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.ticketType = priority;
    }

    @Override
    public String toString() {
        return "{ DepartureAirport:" + departureAirport.getAirportName() + ", DestinationAirport: " + destinationAirport.getAirportName() + ", TicketType: " + ticketType +" }";
    }
}
