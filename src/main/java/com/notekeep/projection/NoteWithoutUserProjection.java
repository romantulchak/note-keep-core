package com.notekeep.projection;

public interface NoteWithoutUserProjection {

    String getId();

    String getTitle();

    String getText();

    boolean isMarked();

    String getBackgroundColor();

    String getBackgroundImage();

    boolean isArchived();

    int getOrder();
}
