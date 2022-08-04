package com.notekeep.payload.request.note;


import com.notekeep.model.enums.BackgroundType;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Contains fields for change Background for note
 */
@Data
public class ChangeBackgroundRequest {
    @NotNull
    private String noteId;

    @NotNull
    private String backgroundName;

    @NotNull
    private BackgroundType type;
}
