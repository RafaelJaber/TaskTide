package com.javanauta.taskscheduler.infrastructure.exceptions;

public class OperationNotPermittedException extends RuntimeException {
    public OperationNotPermittedException(String message) {
        super(message);
    }

  public OperationNotPermittedException(String message, Throwable cause) {
    super(message, cause);
  }
}
