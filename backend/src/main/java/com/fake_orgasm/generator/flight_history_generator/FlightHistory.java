package com.fake_orgasm.generator.flight_history_generator;

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

    /**
     * This method retrieves the departure airport for this flight history.
     *
     * @return The departure airport.
     */
    public Airport getDepartureAirport() {
        return departureAirport;
    }

    /**
     * This method sets the departure airport for this flight history.
     *
     * @param departureAirport The departure airport to set.
     */
    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
     * This method retrieves the destination airport for this flight history.
     *
     * @return The destination airport.
     */
    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * This method sets the destination airport for this flight history.
     *
     * @param destinationAirport The destination airport to set.
     */
    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * This method retrieves the ticket priority for this flight history.
     *
     * @return The ticket priority.
     */
    public Priority getTicketType() {
        return ticketType;
    }

    /**
     * This method sets the ticket priority for this flight history.
     *
     * @param ticketType The ticket priority to set.
     */
    public void setTicketType(Priority ticketType) {
        this.ticketType = ticketType;
    }
}
