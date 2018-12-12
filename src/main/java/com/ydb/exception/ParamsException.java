package com.ydb.exception;

public class ParamsException extends RuntimeException {
    public ParamsException() {
    }

    public ParamsException(String message) {
        super(message);
    }

    public ParamsException(String message, Throwable cause) {
        super(message, cause);
    }
}
