package com.fake_orgasm.flights_management.data_handler.schemas;

public enum FlightSchema implements Schemable {

    ID("id"),
    SOURCE_ID("sourceId"),
    DESTINATION_ID("destinationId"),
    DATE("date"),
    CAPACITY("capacity"),
    TICKETS("tickets");

    private final String file;

    FlightSchema(String file) {
        this.file = file;
    }

    @Override
    public String getField() {
        return file;
    }
}