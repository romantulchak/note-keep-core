package com.notekeep.service;

import com.notekeep.payload.request.note.NoteRequest;
import org.springframework.security.core.Authentication;

public interface NoteService {

    void create(NoteRequest noteRequest, Authentication authentication);
}
