package com.notekeep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Contains all name of color and its value
 */
@Data
@AllArgsConstructor
public class NoteBackgroundDTO {
    private String name;
    private String value;
}
