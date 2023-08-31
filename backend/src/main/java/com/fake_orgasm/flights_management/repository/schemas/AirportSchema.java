package com.fake_orgasm.flights_management.repository.schemas;

public enum AirportSchema implements Schemable {

    ID("id"),
    NAME("name"),
    COUNTRY("country"),
    STATE("state");

    private final String file;

    AirportSchema(String file) {
        this.file = file;
    }

    @Override
    public String getField() {
        return file;
    }
}