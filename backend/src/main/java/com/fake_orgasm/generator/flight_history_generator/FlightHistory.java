package com.fake_orgasm.generator.flight_history_generator;

public class FlightHistory {
    private Airport departureAirport;
    private Airport destinationAirport;
    private Priority ticketType;

    public FlightHistory(Airport departureAirport, Airport destinationAirport, Priority ticketType) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.ticketType = ticketType;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Priority getTicketType() {
        return ticketType;
    }

    public void setTicketType(Priority ticketType) {
        this.ticketType = ticketType;
    }
}
