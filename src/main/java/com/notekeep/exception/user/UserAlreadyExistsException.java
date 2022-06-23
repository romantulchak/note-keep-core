package com.notekeep.exception.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("user.already.exists");
    }
}
