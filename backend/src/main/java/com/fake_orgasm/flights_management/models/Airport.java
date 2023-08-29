package com.fake_orgasm.flights_management.models;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is the airport model.
 */
@Getter
@Setter
public class Airport {
    private String id;
    private String name;
    private String country;
    private String state;

    /**
     * This is the constructor for this class.
     *
     * @param name The name of the airport.
     * @param country     The country where the airport is located.
     * @param state       The state where the airport is located.
     */
    public Airport(String name, String country, String state) {
        this.name = name;
        this.country = country;
        this.state = state;
    }
}