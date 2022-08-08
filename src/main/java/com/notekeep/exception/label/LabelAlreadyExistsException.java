package com.notekeep.exception.label;

public class LabelAlreadyExistsException extends RuntimeException {

    public LabelAlreadyExistsException(String name) {
        super(name);
    }
}
