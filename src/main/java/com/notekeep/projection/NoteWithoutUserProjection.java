package com.notekeep.projection;

public interface NoteWithoutUserProjection {

    String getId();

    String getTitle();

    String getText();

    boolean getIsMarked();

    String getBackgroundColor();

    String getBackgroundImage();

    boolean getIsArchived();

    int getOrder();
}
