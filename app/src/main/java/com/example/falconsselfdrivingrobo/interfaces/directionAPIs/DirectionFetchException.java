package com.example.falconsselfdrivingrobo.interfaces.directionAPIs;

public class DirectionFetchException extends Exception {
    public final String message ;

    public DirectionFetchException(String errorMessage) {
        this.message = errorMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
