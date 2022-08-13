package com.notekeep.dto;

import lombok.Data;

@Data
public class LabelDTO {
    private String id;
    private String name;

    public LabelDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
