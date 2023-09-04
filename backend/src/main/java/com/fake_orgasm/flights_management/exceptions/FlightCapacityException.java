package com.fake_orgasm.flights_management.exceptions;

import com.fake_orgasm.flights_management.models.Flight;

/**
 * This class has the responsibility to generate a custom exception class
 * to represent an exception when the flight capacity is invalid.
 * <p>
 * This exception is thrown when the flight capability falls outside the acceptable range
 * defined by Flight.MIN_CAPACITY and Flight.MAX_CAPACITY.
 */
public class FlightCapacityException extends Exception {

    /**
     * Constructs a new FlightCapacityException with a default error message.
     * <p>
     * The default error message indicates that the flight capability should be between
     * min and max flight capacity.
     */
    public FlightCapacityException() {
        super("Invalid flight capability, the capability should be between "
                + Flight.MIN_CAPACITY + " - " + Flight.MAX_CAPACITY);
    }

}