package com.fake_orgasm.generator.flight_history_generator;

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

    /**
     * This method returns the name of the airport.
     *
     * @return The airport's name.
     */
    public String getAirportName() {
        return airportName;
    }

    /**
     * This method sets the name of the airport.
     *
     * @param airportName The name to set for the airport.
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    /**
     * This method returns the country where the airport is located.
     *
     * @return The country of the airport.
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method sets the country where the airport is located.
     *
     * @param country The country to set for the airport.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method returns the state where the airport is located.
     *
     * @return The state of the airport.
     */
    public String getState() {
        return state;
    }

    /**
     * This method sets the state where the airport is located.
     *
     * @param state The state to set for the airport.
     */
    public void setState(String state) {
        this.state = state;
    }
}
