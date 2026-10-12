package com.example.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RegistrationExceptionHandler {

    @ExceptionHandler(UnderageRegistrationException.class)
    public ResponseEntity<RegistrationError> handleUnderage(UnderageRegistrationException exception) {
        return ResponseEntity.badRequest()
                .body(new RegistrationError(exception.getMessage(), exception.getMinimumAge()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RegistrationError> handleInvalidRequest(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new RegistrationError("Email or age is invalid.", null));
    }

    public record RegistrationError(String error, Integer minimumAge) {
    }
}