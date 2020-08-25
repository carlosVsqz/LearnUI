package com.starterkit.springboot.brs.exception;

public class UserAlreadyConfirmedException extends RuntimeException {

    public UserAlreadyConfirmedException(String message) {
        super(message);
    }
}
