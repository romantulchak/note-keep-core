package com.notekeep.dto;

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

}
