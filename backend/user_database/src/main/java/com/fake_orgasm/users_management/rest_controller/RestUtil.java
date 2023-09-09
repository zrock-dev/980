package com.fake_orgasm.users_management.rest_controller;

import com.fake_orgasm.users_management.services.exceptions.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This utility class provides methods for building RESTful responses.
 */
public class RestUtil {

    /**
     * Builds a ResponseEntity with the provided message and HTTP status code.
     *
     * @param message     The message to include in the response.
     * @param httpStatus  The HTTP status code for the response.
     * @return            A ResponseEntity containing the message and status code.
     */
    public static ResponseEntity<?> buildResponse(String message, HttpStatus httpStatus) {
        RestResponse response = RestResponse.builder()
                .message(message)
                .status(httpStatus.value())
                .build();
        return new ResponseEntity<>(response, httpStatus);
    }
}
