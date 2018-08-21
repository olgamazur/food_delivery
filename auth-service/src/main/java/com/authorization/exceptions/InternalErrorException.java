package com.authorization.exceptions;

public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String cause) {
        super(String.format("Internal error: %s", cause));
    }
}