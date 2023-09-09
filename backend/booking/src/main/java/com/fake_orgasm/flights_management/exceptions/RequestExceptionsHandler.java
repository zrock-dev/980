package com.fake_orgasm.flights_management.exceptions;

import com.fake_orgasm.flights_management.repository.Pagination;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

/**
 * This class is a util class that contains methods for the RestResponse class.
 */
@RestControllerAdvice
public class RequestExceptionsHandler {
    /**
     * Handle the exception when the flight capacity is invalid.
     *
     * @param e the runtime exception
     * @return the response entity.
     */

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<RestResponse> handleException(RuntimeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestResponse error = RestResponse.builder().message(e.getMessage()).status(status.value()).build();
        return new ResponseEntity<>(error, status);
    }

    /**
     * Handles the exception when a user is not found.
     *
     * @param e the UserNotFoundException that was thrown
     * @return a ResponseEntity containing the error details
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<RestResponse> handleException(UserNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RestResponse error = RestResponse.builder().message(e.getMessage()).status(status.value()).build();
        return new ResponseEntity<>(error, status);
    }

    /**
     * Handles the exception of type IllegalArgumentException.
     *
     * @param e the IllegalArgumentException that was thrown
     * @return a ResponseEntity containing a RestResponse with the error details
     */

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<RestResponse> handleException(IllegalArgumentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestResponse error = RestResponse.builder().message(e.getMessage()).status(status.value()).build();
        return new ResponseEntity<>(error, status);
    }

    /**
     * Handles the exception of type IllegalArgumentException.
     *
     * @param e the IllegalArgumentException that was thrown
     * @return a ResponseEntity containing a RestResponse with the error details
     */

    @ExceptionHandler(value = EmptyContentException.class)
    public ResponseEntity<Pagination> handleException(EmptyContentException e) {
        HttpStatus status = HttpStatus.OK;
        Pagination flightList = new Pagination(0, 0, new ArrayList<>(), 0, 0);
        return new ResponseEntity<>(flightList, status);
    }


}
