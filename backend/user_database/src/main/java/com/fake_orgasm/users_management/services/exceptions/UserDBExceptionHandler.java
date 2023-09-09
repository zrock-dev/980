package com.fake_orgasm.users_management.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the User Database REST API.
 * This class handles exceptions thrown by the user management services
 * and returns appropriate HTTP responses.
 */
@RestControllerAdvice
public class UserDBExceptionHandler {
    /**
     * Handles the exception of type RuntimeException.
     *
     * @param e the RuntimeException object to handle
     * @return the ResponseEntity object containing the error and status
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<RestResponse> handleException(RuntimeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestResponse error =
                RestResponse.builder().message(e.getMessage()).status(status.value()).build();
        return new ResponseEntity<>(error, status);
    }

    /**
     * Handles the exception of type RuntimeException.
     *
     * @param e the RuntimeException object to handle
     * @return the ResponseEntity object containing the error and status
     */
    @ExceptionHandler(value = NonexistentUserException.class)
    public ResponseEntity<RestResponse> handleException(NonexistentUserException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RestResponse error =
                RestResponse.builder().message(e.getMessage()).status(status.value()).build();
        return new ResponseEntity<>(error, status);
    }
}
