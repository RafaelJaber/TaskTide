package com.javanauta.bffscheduler.infrastructure.exception;

public class FeignGeneralException extends RuntimeException {
    public FeignGeneralException(String message) {
        super(message);
    }

    public FeignGeneralException(String message, Throwable cause) {
      super(message, cause);
    }
}
