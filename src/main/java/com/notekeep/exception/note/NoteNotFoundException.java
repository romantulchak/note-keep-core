package com.notekeep.exception.note;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException() {
        super("note.not.found.exception");
    }
}
