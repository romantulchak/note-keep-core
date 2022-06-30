package com.notekeep.payload.request.note;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NoteRequest {

    @Size(min = 1, max = 999, message = "note.title.length")
    private String title;

    @Size(min = 1, max = 7800, message = "note.text.length")
    private String text;

    private String backgroundImage;

    private String backgroundColor;
}
