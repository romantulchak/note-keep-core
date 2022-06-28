package com.notekeep.model.enums;

import com.notekeep.constant.AppConstant;
import com.notekeep.dto.NoteBackgroundDTO;

import java.util.EnumSet;
import java.util.List;

public enum NoteBackground {

    CELEBRATION("celebration.svg"),
    EAT("eat.svg"),
    MUSIC("music.svg"),
    NOTES("notes.svg"),
    PLACES("places.svg"),
    PRODUCTS("products.svg"),
    RECIPE("recipe.svg"),
    TRAVEL("travel.svg"),
    VIDEO("video.svg");

    private String path;

    NoteBackground(String path) {
        this.path = String.join("/", AppConstant.HOST_FILE_NAME, path);
    }

    public static List<NoteBackgroundDTO> getNoteBackgrounds(){
        return EnumSet.allOf(NoteBackground.class).stream()
                .map(noteBackground -> new NoteBackgroundDTO(noteBackground.name(), noteBackground.getPath()))
                .toList();
    }

    public String getPath() {
        return path;
    }
}
