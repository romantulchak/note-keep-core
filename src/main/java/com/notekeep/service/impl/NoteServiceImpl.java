package com.notekeep.service.impl;

import com.notekeep.component.Transformer;
import com.notekeep.dto.NoteBackgroundDTO;
import com.notekeep.dto.NoteDTO;
import com.notekeep.exception.user.UserNotFoundException;
import com.notekeep.model.Note;
import com.notekeep.model.User;
import com.notekeep.model.enums.NoteBackground;
import com.notekeep.model.enums.NoteColor;
import com.notekeep.payload.request.note.NoteRequest;
import com.notekeep.repository.NoteRepository;
import com.notekeep.repository.UserRepository;
import com.notekeep.service.NoteService;
import com.notekeep.utility.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final Transformer transformer;

    /**
     * Creates note by data from client
     *
     * @param noteRequest    contains necessary data for note
     * @param authentication to get user in system
     */
    @Override
    public void create(NoteRequest noteRequest, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        Note note = new Note()
                .setTitle(noteRequest.getTitle())
                .setText(noteRequest.getText())
                .setBackgroundImage(NoteBackground.getNoteBackgroundByName(noteRequest.getBackgroundImage()))
                .setBackgroundColor(NoteColor.getNoteColorValueByName(noteRequest.getBackgroundColor()))
                .setUser(user)
                .setOrder(1);
        noteRepository.save(note);
    }

    /**
     * Deletes note by id
     *
     * @param id note that will be deleted
     */
    @Override
    public void delete(String id) {
        noteRepository.deleteById(id);
    }

    /**
     * Gets all existing colors for note
     *
     * @return {@link NoteBackgroundDTO} with color name and its value
     */
    @Override
    public List<NoteBackgroundDTO> getAllNoteColors() {
        return NoteColor.getColors();
    }

    /**
     * Gets all existing backgrounds for note
     *
     * @return {@link NoteBackgroundDTO} with background name and its value
     */
    @Override
    public List<NoteBackgroundDTO> getAllBackgroundColors() {
        return NoteBackground.getNoteBackgrounds();
    }

    /**
     * Gets user notes (40 elements per page)
     *
     * @param page current page
     * @param authentication to get user email in system
     * @return list {@link NoteDTO} of notes for user
     */
    @Override
    public List<NoteDTO> getNotes(String page, Authentication authentication) {
        int pageNumber = PageableHelper.getPageNumberFromString(page);
        Pageable pageable = PageRequest.of(pageNumber, 40);
        return noteRepository.findNotesByUserEmailOrderByOrder(authentication.getName(), pageable)
                .getContent()
                .stream()
                .map(transformer::convertNoteToDTO)
                .toList();
    }
}
