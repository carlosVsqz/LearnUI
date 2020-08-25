package com.starterkit.springboot.brs.exception;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

public class FirebaseUserNotExistsException extends AuthenticationCredentialsNotFoundException {

    public FirebaseUserNotExistsException() {
        super("User Not Fount");
    }
}
