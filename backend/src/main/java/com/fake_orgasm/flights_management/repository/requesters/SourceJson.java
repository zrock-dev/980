package com.fake_orgasm.flights_management.repository.requesters;

/**
 * This enum class has the responsibility of defining different data sources
 * in JSON format for the program.
 */
public enum SourceJson {

    AIRPORTS("src/main/resources/DataBase/Flights/Airports.json"),
    FLIGHTS("src/main/resources/DataBase/Flights/Flights.json"),
    TICKETS("src/main/resources/DataBase/Flights/Tickets.json");

    private final String source;

    /**
     * This method constructs a SourceJson enum constant with the provided source path.
     *
     * @param source The path to the JSON data source.
     */
    SourceJson(String source) {
        this.source = source;
    }

    /**
     * This method gets the path to the JSON data source associated with this enum constant.
     *
     * @return The path to the JSON data source.
     */
    public String getSource() {
        return source;
    }
}