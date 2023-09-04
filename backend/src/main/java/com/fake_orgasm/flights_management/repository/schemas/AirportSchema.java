package com.fake_orgasm.flights_management.repository.schemas;

/**
 * This enum class has the responsibility of defining the schema for airport data,
 * implementing the Schemable interface.
 */
public enum AirportSchema implements Schemable {

    ID("id"),
    NAME("name"),
    COUNTRY("country"),
    STATE("state");

    private final String file;

    /**
     * This method constructs an AirportSchema enum constant with the provided schema field name.
     *
     * @param file The name of the field in the airport data schema.
     */
    AirportSchema(String file) {
        this.file = file;
    }

    /**
     * This method gets the name of the field in the airport data schema associated
     * with this enum constant.
     *
     * @return The name of the schema field.
     */
    @Override
    public String getField() {
        return file;
    }
}