package com.chandraedu.api.transactions.exception;

import com.chandraedu.api.transactions.exception.model.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Locale;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(FieldValidationException.class)
    public final ResponseEntity<ApiError> handleFieldValidationException(
            final FieldValidationException fieldValidationException) {

        final ApiError apiError = ApiError.builder()
                .type(BAD_REQUEST.getReasonPhrase())
                .code(BAD_REQUEST.value())
                .message(getMessage(fieldValidationException.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordFoundException.class)
    public final ResponseEntity<ApiError> handleNoRecordFoundException(
            final NoRecordFoundException noRecordFoundException) {

        final ApiError apiError = apiError(OK, noRecordFoundException.getMessage());
        return new ResponseEntity<>(apiError, OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ApiError> handleIllegalArgumentException(
            final IllegalArgumentException illegalArgumentException) {

        final ApiError apiError = apiError(BAD_REQUEST, illegalArgumentException.getMessage());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    private String getMessage(final String message) {

        final Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(message, null, locale);
        } catch (NoSuchMessageException e) {
            return message;
        }
    }

    private ApiError apiError(HttpStatus httpStatus,
                              String message) {

        return ApiError.builder()
                .type(httpStatus.getReasonPhrase())
                .code(httpStatus.value())
                .message(getMessage(message))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
