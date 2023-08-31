package com.fake_orgasm.flights_management.repository.requesters;

public enum SourceJson {

    AIRPORTS("src/main/resources/DataBase/Flights/Airports.json"),
    FLIGHTS("src/main/resources/DataBase/Flights/Flights.json"),
    TICKETS("src/main/resources/DataBase/Flights/Tickets.json");

    private final String source;

    SourceJson(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}