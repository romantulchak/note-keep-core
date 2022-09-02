package com.notekeep.controller;

import com.notekeep.dto.NoteBackgroundDTO;
import com.notekeep.dto.NoteDTO;
import com.notekeep.payload.request.label.AddLabelToNoteRequest;
import com.notekeep.payload.request.note.ChangeBackgroundRequest;
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
    public NoteDTO create(@Valid @RequestBody NoteRequest noteRequest, Authentication authentication) {
        return noteService.create(noteRequest, authentication);
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

    @GetMapping("/label/{name}")
    @PreAuthorize("isAuthenticated()")
    public List<NoteDTO> getNotesByLabelName(@PathVariable("name") String name, @RequestParam(value = "page", defaultValue = "0") String page, Authentication authentication) {
        return noteService.getNotesByLabelName(name, page, authentication);
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

    @PutMapping("/change-background")
    public void changeBackground(@Valid @RequestBody ChangeBackgroundRequest changeBackgroundRequest) {
        noteService.changeNoteBackground(changeBackgroundRequest);
    }

    @PutMapping("/add-label")
    @PreAuthorize("isAuthenticated()")
    public void addLabelToNote(@Valid @RequestBody AddLabelToNoteRequest addLabelToNoteRequest) {
        noteService.addLabelToNote(addLabelToNoteRequest);
    }

    @PutMapping("/add-to-archive")
    @PreAuthorize("isAuthenticated() && @userNoteAccess.hasAccess(#authentication, #noteId)")
    public void addNoteToArchive(@RequestBody String noteId, Authentication authentication) {
        noteService.addNoteToArchive(noteId, authentication);
    }

    @PutMapping("/remove-from-archive")
    @PreAuthorize("isAuthenticated() && @userNoteAccess.hasAccess(#authentication, #noteId)")
    public void removeNoteFromArchive(@RequestBody String noteId, Authentication authentication) {
        noteService.removeNoteFromArchive(noteId, authentication);
    }

    @GetMapping("/archived")
    @PreAuthorize("isAuthenticated()")
    public List<NoteDTO> getArchivedNotes(@RequestParam(value = "page", defaultValue = "0") String page, Authentication authentication) {
        return noteService.getArchivedNotes(page, authentication);
    }
}
