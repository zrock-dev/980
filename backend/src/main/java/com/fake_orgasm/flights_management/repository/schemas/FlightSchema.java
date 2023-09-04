package com.fake_orgasm.flights_management.repository.schemas;

/**
 * This enum class has the responsibility of defining the schema for flight data,
 * implementing the Schemable interface.
 */
public enum FlightSchema implements Schemable {

    ID("id"),
    SOURCE_ID("sourceId"),
    DESTINATION_ID("destinationId"),
    DATE("date"),
    CAPACITY("capacity"),
    TICKETS("tickets");

    private final String file;

    /**
     * This method constructs a FlightSchema enum constant with the provided schema field name.
     *
     * @param file The name of the field in the flight data schema.
     */
    FlightSchema(String file) {
        this.file = file;
    }

    /**
     * This method gets the name of the field in the flight data schema associated
     * with this enum constant.
     *
     * @return The name of the schema field.
     */
    @Override
    public String getField() {
        return file;
    }
}