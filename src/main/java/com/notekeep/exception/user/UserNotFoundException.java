package com.notekeep.exception.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("user.not.found");
    }
}
