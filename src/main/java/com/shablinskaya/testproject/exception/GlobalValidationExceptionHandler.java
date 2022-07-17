package com.shablinskaya.testproject.exception;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class GlobalValidationExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String STATUS_FIELD = "status";
    private static final String TIMESTAMP_FIELD = "timestamp";
    private static final String MESSAGE_FIELD = "message";
    private static final String ERRORS_FIELD = "errors";

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType();
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP_FIELD, Instant.now());
        body.put(MESSAGE_FIELD, "This should be application specific");
        body.put(ERRORS_FIELD, error);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP_FIELD, Instant.now());
        body.put(STATUS_FIELD, status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put(ERRORS_FIELD, errors);

        return new ResponseEntity<>(body, headers, status);
    }
}

