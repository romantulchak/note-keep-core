package com.notekeep.model.enums;

import com.notekeep.dto.NoteBackgroundDTO;

import java.util.EnumSet;
import java.util.List;

public enum NoteColor {

    RED("#f28b82"),
    ORANGE("#fbbc04"),
    YELLOW("#fff475"),
    GREEN("#ccff90"),
    BLUE_GREEN("#a7ffeb"),
    BLUE("cbf0f8"),
    BLACK_BLUE("#aecbfa"),
    PURPLE("#d7aefb"),
    PINK("#fdcfe8"),
    BROWN("#e6c9a8"),
    GREY("#e8eaed");

    private String color;

    NoteColor(String color) {
        this.color = color;
    }

    /**
     * Gets all existing colors for note
     *
     * @return {@link NoteBackgroundDTO} with color name and its value
     */
    public static List<NoteBackgroundDTO> getColors() {
        return EnumSet.allOf(NoteColor.class)
                .stream()
                .map(noteColor -> new NoteBackgroundDTO(noteColor.name(), noteColor.getColor()))
                .toList();
    }

    /**
     * Gets value from enum by its name
     *
     * @param name to get its value
     * @return value of enum by name or if value doesn't exist return empty string
     */
    public static String getNoteColorValueByName(String name) {
        try {
            return NoteColor.valueOf(name).getColor();
        } catch (IllegalArgumentException | NullPointerException e){
            return "";
        }
    }

    public String getColor() {
        return color;
    }
}
