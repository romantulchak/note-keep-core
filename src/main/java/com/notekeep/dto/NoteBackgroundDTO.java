package com.notekeep.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Contains all name of color and its value
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoteBackgroundDTO {
    private String name;
    private String value;
    private String fullPathToImage;

    /**
     * For creating NoteBackgroundDTO without additional fields
     *
     * @param name of field
     * @param value of field
     */
    public NoteBackgroundDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * For creating NoteBackgroundDTO with path to full image
     * for BackgroundImage
     *
     * @param name of filed
     * @param value of field
     * @param fullPathToImage path to full image
     */
    public NoteBackgroundDTO(String name, String value, String fullPathToImage) {
        this.name = name;
        this.value = value;
        this.fullPathToImage = fullPathToImage;
    }
}
