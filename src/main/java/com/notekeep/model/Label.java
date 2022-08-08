package com.notekeep.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Document("label")
public class Label {

    @Id
    private String id;

    @Size(min = 1, max = 50, message = "label.name.size")
    private String name;

    @NotNull
    private User user;

    private List<Note> notes;

    public Label() {
    }

    public Label(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
