package com.starterkit.springboot.brs.exception;

public class DeliverableDoesNotBelongToUser extends RuntimeException {
    public DeliverableDoesNotBelongToUser(String message) {
        super(message);
    }
}
