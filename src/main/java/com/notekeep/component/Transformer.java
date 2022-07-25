package com.notekeep.component;

import com.notekeep.dto.NoteDTO;
import com.notekeep.model.Note;
import com.notekeep.model.enums.NoteBackground;
import com.notekeep.model.enums.NoteColor;
import com.notekeep.projection.NoteWithoutUserProjection;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Transformer {

    private final ModelMapper modelMapper;

    /**
     * Transforms {@link NoteWithoutUserProjection} to {@link NoteDTO}
     *
     * @param noteWithoutUserProjection to be transformed
     * @return transformed {@link NoteDTO}
     */
    public NoteDTO convertNoteToDTO(NoteWithoutUserProjection noteWithoutUserProjection) {
        return modelMapper.map(noteWithoutUserProjection, NoteDTO.class)
                .setBackgroundColor(NoteBackground.getNoteBackgroundImageNameByValue(noteWithoutUserProjection.getBackgroundImage()))
                .setBackgroundImage(NoteColor.getNoteBackgroundColorNameByValue(noteWithoutUserProjection.getBackgroundColor()));

    }

    /**
     * Transforms {@link Note} to {@link NoteDTO}
     *
     * @param note to be transformed
     * @return transformed {@link NoteDTO}
     */
     public NoteDTO convertNoteToDTO(Note note) {
        return modelMapper.map(note, NoteDTO.class)
                .setBackgroundColor(NoteBackground.getNoteBackgroundImageNameByValue(note.getBackgroundImage()))
                .setBackgroundImage(NoteColor.getNoteBackgroundColorNameByValue(note.getBackgroundColor()));

    }



}
