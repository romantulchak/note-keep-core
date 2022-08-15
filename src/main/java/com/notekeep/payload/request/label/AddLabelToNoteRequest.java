package com.notekeep.payload.request.label;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddLabelToNoteRequest {

    @NotNull
    private String noteId;

    @NotNull
    private String labelId;
}
