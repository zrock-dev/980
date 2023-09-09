package com.fake_orgasm.flights_management.exceptions;

import lombok.Builder;
import lombok.Data;

/**
 * A class representing an exception that occurred during the response.
 */
@Data
@Builder
public class RestResponse {
    /**
     * The error message.
     */
    private String message;

    /**
     * The HTTP status code associated with the error.
     */
    private int status;
}
