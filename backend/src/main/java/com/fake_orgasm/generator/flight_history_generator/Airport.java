package com.fake_orgasm.generator.flight_history_generator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * This class is the airport model.
 */
public class Airport {
    private String airportName;
    private String country;
    private String state;

    /**
     * This is the constructor for this class.
     *
     * @param airportName The name of the airport.
     * @param country     The country where the airport is located.
     * @param state       The state where the airport is located.
     */
    public Airport(String airportName, String country, String state) {
        this.airportName = airportName;
        this.country = country;
        this.state = state;
    }
}
