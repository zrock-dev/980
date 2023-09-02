package com.fake_orgasm.flights_management.exceptions;

import com.fake_orgasm.flights_management.models.Flight;

public class FlightCapacityException extends Exception {

    public FlightCapacityException() {
        super("Invalid flight capability, the capability should be between "
                + Flight.MIN_CAPACITY + " - " + Flight.MAX_CAPACITY);
    }

}