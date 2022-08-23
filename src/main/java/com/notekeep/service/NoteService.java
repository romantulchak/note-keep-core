package com.notekeep.service;

import com.notekeep.dto.NoteBackgroundDTO;
import com.notekeep.dto.NoteDTO;
import com.notekeep.payload.request.label.AddLabelToNoteRequest;
import com.notekeep.payload.request.note.ChangeBackgroundRequest;
import com.notekeep.payload.request.note.NoteRequest;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

public interface NoteService {

    NoteDTO create(NoteRequest noteRequest, Authentication authentication);

    void delete(String id);

    Map<String, List<NoteBackgroundDTO>> getAllBackgroundColors();

    List<NoteDTO> getNotes(String page, Authentication authentication);

    List<NoteDTO> getNotesByLabelName(String labelName, String page, Authentication authentication);

    int changeOrderForNote(String id);

    void changeNoteBackground(ChangeBackgroundRequest changeBackgroundRequest);

    void addLabelToNote(AddLabelToNoteRequest addLabelToNoteRequest);

    void addNoteToArchive(String id, Authentication authentication);

    List<NoteDTO> getArchivedNotes(String page, Authentication authentication);
}
