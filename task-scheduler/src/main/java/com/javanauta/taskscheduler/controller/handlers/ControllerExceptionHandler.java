package com.javanauta.taskscheduler.controller.handlers;

import com.javanauta.taskscheduler.business.dto.response.errors.CustomErrorResponse;
import com.javanauta.taskscheduler.business.dto.response.errors.ValidationErrorResponse;
import com.javanauta.taskscheduler.infrastructure.exceptions.OperationNotPermittedException;
import com.javanauta.taskscheduler.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.taskscheduler.infrastructure.exceptions.UserNotLoggedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<CustomErrorResponse> operationNotPermittedExceptionHandler(OperationNotPermittedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        CustomErrorResponse err = getCustomError(status, ex.getMessage(), request);
        return ResponseEntity.status(status).body(err);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomErrorResponse err = getCustomError(status, ex.getMessage(), request);
        return ResponseEntity.status(status).body(err);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UserNotLoggedException.class)
    public ResponseEntity<CustomErrorResponse> userNotLoggedExceptionHandler(UserNotLoggedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        CustomErrorResponse err = getCustomError(status, ex.getMessage(), request);
        return ResponseEntity.status(status).body(err);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationErrorResponse err = new ValidationErrorResponse(OffsetDateTime.now(), status.value(), status.getReasonPhrase(),"Invalid data sent", request.getRequestURI());

        for (FieldError f: ex.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

    private static CustomErrorResponse getCustomError(HttpStatus status, String ex, HttpServletRequest request) {
        return CustomErrorResponse.builder()
                .timestamp(OffsetDateTime.now())
                .httpStatus(status.value())
                .httpError(status.getReasonPhrase())
                .error(ex)
                .path(request.getRequestURI())
                .build();
    }

}
