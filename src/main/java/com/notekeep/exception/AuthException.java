package com.notekeep.exception;

public class AuthException extends RuntimeException {
    public AuthException() {
        super("user.auth.failed");
    }
}
