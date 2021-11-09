package com.egg.web.library.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {

    }

    public NotFoundException(String message) {
        super(message);
    }
}
