package com.starterkit.springboot.brs.exception;

public class MessageDoesNotBelongToUser extends RuntimeException {
    public MessageDoesNotBelongToUser(String message) {
        super(message);
    }
}
