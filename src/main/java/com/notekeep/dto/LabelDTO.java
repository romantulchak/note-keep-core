package com.notekeep.dto;

import lombok.Data;

@Data
public class LabelDTO {
    private String name;

    public LabelDTO(String name) {
        this.name = name;
    }
}
