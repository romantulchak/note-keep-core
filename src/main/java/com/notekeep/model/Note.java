package com.notekeep.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;

@Document("note")
@Getter
@Setter
@Accessors(chain = true)
public class Note {

    @Id
    private String id;

    @Size(min = 1, max = 999)
    private String title;

    @Size(min = 1, max = 7800)
    private String text;

    private boolean isMarked;

    private String background;

    private boolean isArchived;

    private User user;
}