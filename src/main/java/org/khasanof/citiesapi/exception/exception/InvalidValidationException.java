package org.khasanof.citiesapi.exception.exception;

public class InvalidValidationException extends RuntimeException {
    public InvalidValidationException() {
    }

    public InvalidValidationException(String message) {
        super(message);
    }
}
