package com.notekeep.exception.label;

public class LabelNotFoundException extends RuntimeException {

    public LabelNotFoundException() {
        super("label.not.found");
    }
}
