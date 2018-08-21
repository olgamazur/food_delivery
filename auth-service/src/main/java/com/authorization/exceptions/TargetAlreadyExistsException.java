package com.authorization.exceptions;

public class TargetAlreadyExistsException extends RuntimeException {


    public TargetAlreadyExistsException(long id, String string) {
        super(String.format("%s with id %d already exists", string, id));
    }

    public TargetAlreadyExistsException(String name, String string) {
        super(String.format("%s with name %s already exists", string, name));
    }
}
