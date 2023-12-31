package com.fake_orgasm.currency_exchange.rest_controller.exceptions;

import lombok.Builder;
import lombok.Data;

/**
 * A class representing an error response.
 */
@Data
@Builder
public class Error {
    /**
     * The error message.
     */
    private String message;

    /**
     * The HTTP status code associated with the error.
     */
    private int status;
}
