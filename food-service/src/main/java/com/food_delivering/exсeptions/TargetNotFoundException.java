package com.food_delivering.exсeptions;

public class TargetNotFoundException extends RuntimeException {


    public TargetNotFoundException(long id,String string) {
            super(String.format("%s with id %d is not found", string, id));
    }
    public TargetNotFoundException(String name,String string) {
        super(String.format("%s with name %s is not found", string, name));
    }
}
