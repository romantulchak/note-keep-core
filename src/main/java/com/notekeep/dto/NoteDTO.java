package com.notekeep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NoteDTO {
    private String id;
    private String title;
    private String text;
    private boolean isMarked;
    private NoteBackgroundDTO backgroundColor;
    private NoteBackgroundDTO backgroundImage;
    private boolean isArchived;
    private int order;

    @JsonProperty("isArchived")
    public boolean isArchived() {
        return isArchived;
    }

    @JsonProperty("isMarked")
    public boolean isMarked() {
        return isMarked;
    }
}
