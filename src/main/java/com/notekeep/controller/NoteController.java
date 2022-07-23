package com.notekeep.controller;

import com.notekeep.dto.NoteBackgroundDTO;
import com.notekeep.dto.NoteDTO;
import com.notekeep.payload.request.note.NoteRequest;
import com.notekeep.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/note")
@CrossOrigin(value = "*", maxAge = 3600L)
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public void create(@Valid @RequestBody NoteRequest noteRequest, Authentication authentication) {
        noteService.create(noteRequest, authentication);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated() && @userNoteAccess.hasAccess(authentication, #noteId)")
    public void delete(@PathVariable("id") String noteId) {
        noteService.delete(noteId);
    }

    @GetMapping("/backgrounds")
    @PreAuthorize("isAuthenticated()")
    public Map<String, List<NoteBackgroundDTO>> getAllNoteBackground() {
        return noteService.getAllBackgroundColors();
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<NoteDTO> getNotesForUser(@RequestParam(value = "page", defaultValue = "0") String page, Authentication authentication) {
        return noteService.getNotes(page, authentication);
    }

    @PutMapping("/change-order")
    public int changeOrderForNote(@RequestBody String id) {
        return noteService.changeOrderForNote(id);
    }
}
