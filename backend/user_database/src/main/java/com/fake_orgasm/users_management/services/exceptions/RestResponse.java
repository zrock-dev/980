package com.fake_orgasm.users_management.services.exceptions;

import lombok.Builder;
import lombok.Data;

/**
 * A class representing an error response.
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
