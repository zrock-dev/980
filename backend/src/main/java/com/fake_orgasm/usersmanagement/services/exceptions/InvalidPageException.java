package com.fake_orgasm.usersmanagement.services.exceptions;

/**
 * Exception thrown when an invalid page number is provided.
 */
public class InvalidPageException extends Exception {

    /**
     * Constructs an InvalidPageException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidPageException(String message) {
        super(message);
    }
}
