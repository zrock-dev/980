package com.fake_orgasm.flights_management.rest_controller.utils;

import com.fake_orgasm.flights_management.exceptions.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * This class is a util class that contains methods for the RestResponse class.
 */
public class RestUtil {
    /**
     * This method builds a RestResponse object.
     *
     * @param message    the message of the response.
     * @param httpStatus the status of the response.
     * @return the RestResponse object.
     */
    public static ResponseEntity<?> buildResponse(String message, HttpStatus httpStatus) {
        RestResponse response = RestResponse.builder()
                .message(message)
                .status(httpStatus.value())
                .build();
        return new ResponseEntity<>(response, httpStatus);
    }
}
