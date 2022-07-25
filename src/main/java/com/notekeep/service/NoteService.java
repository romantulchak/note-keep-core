package com.notekeep.service;

import com.notekeep.dto.NoteBackgroundDTO;
import com.notekeep.dto.NoteDTO;
import com.notekeep.payload.request.note.NoteRequest;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

public interface NoteService {

    NoteDTO create(NoteRequest noteRequest, Authentication authentication);

    void delete(String id);

    Map<String, List<NoteBackgroundDTO>> getAllBackgroundColors();

    List<NoteDTO> getNotes(String page, Authentication authentication);

    int changeOrderForNote(String id);
}
