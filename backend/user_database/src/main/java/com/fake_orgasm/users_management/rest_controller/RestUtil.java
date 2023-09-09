package com.fake_orgasm.users_management.rest_controller;

import com.fake_orgasm.users_management.services.exceptions.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestUtil {
    public static ResponseEntity<?> buildResponse(String message, HttpStatus httpStatus) {
        RestResponse response =
                RestResponse.builder().message(message).status(httpStatus.value()).build();
        return new ResponseEntity<>(response, httpStatus);
    }
}
