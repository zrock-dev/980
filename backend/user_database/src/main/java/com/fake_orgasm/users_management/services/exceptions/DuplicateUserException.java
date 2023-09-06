package com.fake_orgasm.users_management.services.exceptions;

/**
 * Exception thrown when attempting to create a user with duplicate information.
 */
public class DuplicateUserException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public DuplicateUserException(String message) {
        super(message);
    }
}
