package com.kenzie.app.CustomException;

public class CustomEmptyStringException extends RuntimeException {
    public CustomEmptyStringException (String message) {
        super(message);
    }
}
