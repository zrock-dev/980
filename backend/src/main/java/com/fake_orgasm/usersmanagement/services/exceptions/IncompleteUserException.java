package com.fake_orgasm.usersmanagement.services.exceptions;

/**
 * Exception thrown when a user's properties are incomplete.
 */
public class IncompleteUserException extends Exception {

    /**
     * Constructs an IncompleteUserException with the specified detail message.
     *
     * @param message The detail message.
     */
    public IncompleteUserException(String message) {
        super(message);
    }
}
