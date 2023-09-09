package com.fake_orgasm.flights_management.exceptions;

/**
 * This class has the responsibility to generate a custom exception class to represent
 * an exception when the flight capacity is invalid.
 */
public class EmptyContentException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public EmptyContentException(String message) {
        super(message);
    }
}
