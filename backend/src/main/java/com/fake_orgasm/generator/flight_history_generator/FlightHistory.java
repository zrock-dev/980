package com.fake_orgasm.generator.flight_history_generator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightHistory {
    private Airport departureAirport;
    private Airport destinationAirport;
    private Priority ticketType;

    /**
     * This is the constructor for this class.
     *
     * @param departureAirport   The departure airport for the flight history.
     * @param destinationAirport The destination airport for the flight history.
     * @param priority         The ticket priority for the flight history.
     */
    public FlightHistory(Airport departureAirport, Airport destinationAirport, Priority priority) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.ticketType = priority;
    }
}
