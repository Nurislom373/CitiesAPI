package org.khasanof.citiesapi.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.citiesapi.exception.exception.NotFoundException;
import org.khasanof.citiesapi.response.ApplicationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApplicationError> notFoundException(NotFoundException exception) {
        ApplicationError error = new ApplicationError();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setPath(error.getPath());
        error.setTime(LocalDateTime.now());
        error.setMessage(exception.getMessage());
        error.setRequest(error.getRequest());
        error.setCode("NotFoundException");
        log.debug("handling exception::" + exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
