package org.khasanof.citiesapi.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * This class is used to send ResponseEntity to exceptions that occur in the program.
 *
 * @author Khasanof373
 * @see ApplicationError
 * @since 1.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationError {

    private String code;
    private String path;
    private Integer status;
    private String message;
    private LocalDateTime time;
    private WebRequest request;

    /**
     * This constructor is one of the constructors used to initialize ApplicationError class fields.
     *
     * @param message -> String comes in and field must be not null
     * @param path -> String comes in and field must be not null
     * @param httpStatus -> HttpStatus comes in and field must be not null
     * @since 1.0
     */
    public ApplicationError(String message, String path, HttpStatus httpStatus) {
        this.path = path;
        this.message = message;
        this.time = LocalDateTime.now();
        this.status = httpStatus.value();
        this.code = httpStatus.getReasonPhrase();
    }

    /**
     * This constructor is one of the constructors used to initialize ApplicationError class fields.
     *
     * @param message -> String comes in and field must be not null
     * @param path -> String comes in and field must be not null
     * @param request -> WebRequest comes in and field must be not null
     * @since 1.0
     */
    @Builder
    public ApplicationError(HttpStatus status, String message, String path, WebRequest request) {
        this.path = path;
        this.message = message;
        this.request = request;
        this.status = status.value();
        this.time = LocalDateTime.now();
        this.code = status.getReasonPhrase();
    }
}
