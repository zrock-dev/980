package com.fake_orgasm.flights_management.exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

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

        RestResponse error = RestResponse.builder()
                .message(e.getMessage())
                .status(status.value())
                .build();
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
        RestResponse error = RestResponse.builder()
                .message(e.getMessage())
                .status(status.value())
                .build();
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
        RestResponse error = RestResponse.builder()
                .message(e.getMessage())
                .status(status.value())
                .build();
        return new ResponseEntity<>(error, status);
    }

    /**
     * This method handles the exception of type HttpClientErrorException.
     *
     * @param e the HttpClientErrorException
     * @return a ResponseEntity containing a RestResponse with the error details.
     */
    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<RestResponse> handleException(HttpClientErrorException e) {
        HttpStatus status = (HttpStatus) e.getStatusCode();
        RestResponse error = RestResponse.builder()
                .message(extractMessage(e.getMessage()))
                .status(status.value())
                .build();
        return new ResponseEntity<>(error, status);
    }

    /**
     * Extracts the message from the input string.
     *
     * @param input the input string to extract the message from
     * @return the extracted message if found, otherwise "Http Client Error"
     */
    private static String extractMessage(String input) {
        Pattern pattern = Pattern.compile("\"message\":\"(.*?)\"");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Http Client Error";
        }
    }
}
