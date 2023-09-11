package com.fake_orgasm.users_management.services.exceptions;

/**
 * Exception thrown when a user's properties are incomplete.
 */
public class IncompleteUserException extends RuntimeException {

    /**
     * Constructs an IncompleteUserException with the specified detail message.
     *
     * @param message The detail message.
     */
    public IncompleteUserException(String message) {
        super(message);
    }
}
