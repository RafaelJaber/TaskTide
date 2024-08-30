package com.javanauta.bffscheduler.controller.handlers;

import com.javanauta.bffscheduler.business.dto.response.errors.CustomErrorResponse;
import com.javanauta.bffscheduler.infrastructure.exception.FeignMicroservicesClientException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<CustomErrorResponse> invalidPasswordRecoverTokenExceptionHandler(MissingRequestHeaderException ex, HttpServletRequest request) {
        if (ex.getHeaderName().equals("Authorization")) {
            HttpStatus status = HttpStatus.UNAUTHORIZED;;
            CustomErrorResponse err = getCustomError(status, "You need to be logged in to access this resource.", request);
            return ResponseEntity.status(status).body(err);
        }
        HttpStatus status = HttpStatus.BAD_REQUEST;;
        CustomErrorResponse err = getCustomError(status, ex.getMessage(), request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(FeignMicroservicesClientException.class)
    public ResponseEntity<CustomErrorResponse> handleFeignClientException(FeignMicroservicesClientException ex) {
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .timestamp(ex.getInternalTimestamp())
                .httpStatus(ex.getInternalHttpStatus())
                .httpError(ex.getInternalHttpError())
                .error(ex.getInternaleError())
                .path(ex.getInternalPath())
                .build();

        return ResponseEntity.status(ex.getInternalHttpStatus()).body(errorResponse);
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
