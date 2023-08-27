package com.fake_orgasm.generator.flight_history_generator;

public class Airport {
    private String airportName;
    private String country;
    private String state;

    public Airport(String airportName, String country, String state) {
        this.airportName = airportName;
        this.country = country;
        this.state = state;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
