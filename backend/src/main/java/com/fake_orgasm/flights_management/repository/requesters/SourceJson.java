package com.fake_orgasm.flights_management.repository.requesters;

public enum SourceJson {

    AIRPORTS("src/main/resources/airports.json"),
    FLIGHTS("src/main/resources/flights.json"),
    TICKETS("src/main/resources/tickets.json");

    private final String source;

    SourceJson(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}