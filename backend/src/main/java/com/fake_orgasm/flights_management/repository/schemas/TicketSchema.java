package com.fake_orgasm.flights_management.repository.schemas;

/**
 * This enum class has the responsibility of defining the schema for ticket data,
 * implementing the Schemable interface.
 */
public enum TicketSchema implements Schemable {

    ID("id"),
    NUMBER("number"),
    PRIORITY("priority"),
    USER_ID("userId"),
    FLIGHT_ID("flightId");

    private final String file;

    /**
     * This method constructs a TicketSchema enum constant with the provided schema field name.
     *
     * @param file The name of the field in the ticket data schema.
     */
    TicketSchema(String file) {
        this.file = file;
    }

    /**
     * This method gets the name of the field in the ticket data schema associated
     * with this enum constant.
     *
     * @return The name of the schema field.
     */
    @Override
    public String getField() {
        return file;
    }
}