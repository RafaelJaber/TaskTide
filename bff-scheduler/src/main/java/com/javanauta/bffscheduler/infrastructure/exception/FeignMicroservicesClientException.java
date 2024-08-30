package com.javanauta.bffscheduler.infrastructure.exception;

import lombok.Getter;

import java.time.OffsetDateTime;


@Getter
public class FeignMicroservicesClientException extends RuntimeException {
    private final OffsetDateTime internalTimestamp;
    private final Integer internalHttpStatus;
    private final String internalHttpError;
    private final String internaleError;
    private final String internalPath;

    public FeignMicroservicesClientException(OffsetDateTime timestamp, Integer httpStatus, String httpError, String error, String path) {
        this.internalTimestamp = timestamp;
        this.internalHttpStatus = httpStatus;
        this.internalHttpError = httpError;
        this.internaleError = error;
        this.internalPath = path;
    }
}
