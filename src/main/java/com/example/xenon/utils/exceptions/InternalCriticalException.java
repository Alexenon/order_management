package com.example.xenon.utils.exceptions;

public class InternalCriticalException extends RuntimeException {

    public InternalCriticalException(String message) {
        super(message);
    }

    public InternalCriticalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalCriticalException(Throwable cause) {
        super(cause);
    }
}
