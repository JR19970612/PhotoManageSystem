package com.ydb.exception;

public class FomatTypeException extends RuntimeException {
    public FomatTypeException() {
    }

    public FomatTypeException(String message) {
        super(message);
    }

    public FomatTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
