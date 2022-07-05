package com.notekeep.service;

import com.notekeep.dto.NoteBackgroundDTO;
import com.notekeep.dto.NoteDTO;
import com.notekeep.payload.request.note.NoteRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NoteService {

    void create(NoteRequest noteRequest, Authentication authentication);

    void delete(String id);

    List<NoteBackgroundDTO> getAllNoteColors();

    List<NoteBackgroundDTO> getAllBackgroundColors();

    List<NoteDTO> getNotes(String page, Authentication authentication);
}
