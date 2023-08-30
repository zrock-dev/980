package com.fake_orgasm.flights_management.data_handler.schemas;

public enum TicketSchema implements Schemable {

    ID("id"),
    NUMBER("number"),
    PRIORITY("priority"),
    USER_ID("userId"),
    FLIGHT_ID("flightId");

    private final String file;

    TicketSchema(String file) {
        this.file = file;
    }

    @Override
    public String getField() {
        return file;
    }
}