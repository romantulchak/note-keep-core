package com.notekeep.service;

import com.notekeep.dto.NoteColorDTO;
import com.notekeep.payload.request.note.NoteRequest;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NoteService {

    void create(NoteRequest noteRequest, Authentication authentication);

    void delete(String id);

    List<NoteColorDTO> getAllNoteColors();
}
