package com.notekeep.controller;

import com.notekeep.payload.request.note.NoteRequest;
import com.notekeep.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/note")
@CrossOrigin(value = "*", maxAge = 3600L)
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public void create(@Valid @RequestBody NoteRequest noteRequest, Authentication authentication){
        noteService.create(noteRequest, authentication);
    }
}
