package com.ydb.exception;

public class ErrorFileFormatException extends RuntimeException {
    public ErrorFileFormatException() {
    }

    public ErrorFileFormatException(String message) {
        super(message);
    }

    public ErrorFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
