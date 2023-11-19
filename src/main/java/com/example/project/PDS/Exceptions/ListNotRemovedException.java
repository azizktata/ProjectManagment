package com.example.project.PDS.Exceptions;

public class ListNotRemovedException extends RuntimeException{
    public ListNotRemovedException(String message) {
        super(message);
    }

    public ListNotRemovedException(String message, Throwable cause) {
        super(message, cause);
    }
}
