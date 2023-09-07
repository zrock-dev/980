package com.fake_orgasm.currency_exchange.rest_controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionMoneyExchangeHandler {

    /**
     * Handles the exception of type RuntimeException.
     *
     * @param e the RuntimeException object to handle
     * @return the ResponseEntity object containing the error and status
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Error> handleException(RuntimeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Error error =
                Error.builder().message(e.getMessage()).status(status.value()).build();
        return new ResponseEntity<>(error, status);
    }
}
