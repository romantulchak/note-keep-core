package com.notekeep.model.enums;

import com.notekeep.constant.AppConstant;
import com.notekeep.dto.NoteBackgroundDTO;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public enum NoteBackground {

    CELEBRATION("celebration.svg", "celebration-full.svg"),
    EAT("eat.svg", "eat-full.svg"),
    MUSIC("music.svg", "music-full.svg"),
    NOTES("notes.svg", "notes-full.svg"),
    PLACES("places.svg", "places-full.svg"),
    PRODUCTS("products.svg", "products-full.svg"),
    RECIPE("recipe.svg", "recipe-full.svg"),
    TRAVEL("travel.svg", "travel-full.svg"),
    VIDEO("video.svg", "video-full.svg");

    private String path;
    private String pathToFullImage;

    NoteBackground(String path, String pathToFullImage) {
        this.path = String.join("/", AppConstant.HOST_FILE_NAME, path);
        this.pathToFullImage = String.join("/", AppConstant.HOST_FILE_NAME, pathToFullImage);
    }

    public static List<NoteBackgroundDTO> getNoteBackgrounds(){
        return EnumSet.allOf(NoteBackground.class).stream()
                .map(noteBackground -> new NoteBackgroundDTO(noteBackground.name(), noteBackground.getPath(), noteBackground.getPathToFullImage()))
                .toList();
    }

    /**
     * Gets value from enum by its name
     *
     * @param name to get its value
     * @return value of enum by name or if value doesn't exist return empty string
     */
    public static String getNoteBackgroundByName(String name){
        try {
            return NoteBackground.valueOf(name).getPath();
        } catch (IllegalArgumentException | NullPointerException e){
            return  "";
        }
    }

    /**
     * Gets name from enum by its value
     *
     * @param value to get its name
     * @return {@link NoteBackgroundDTO} with name and value
     */
    public static NoteBackgroundDTO getNoteBackgroundImageNameByValue(String value){
        Map<String, String> backgroundNameAndFullPath = EnumSet.allOf(NoteBackground.class).stream()
                .filter(noteBackground -> noteBackground.getPath().equals(value))
                .findFirst()
                .map(noteBackground -> Map.of(AppConstant.NAME_KEY_WORD, noteBackground.name(), AppConstant.FULL_IMAGE_PATH_KEY_WORD, noteBackground.getPathToFullImage()))
                .orElse(Map.of());
        return new NoteBackgroundDTO(backgroundNameAndFullPath.get(AppConstant.NAME_KEY_WORD), value, backgroundNameAndFullPath.get(AppConstant.FULL_IMAGE_PATH_KEY_WORD));
    }

    public String getPath() {
        return path;
    }

    public String getPathToFullImage() {
        return pathToFullImage;
    }
}
