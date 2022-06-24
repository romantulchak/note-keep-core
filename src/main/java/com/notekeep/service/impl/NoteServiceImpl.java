package com.notekeep.service.impl;

import com.notekeep.exception.user.UserNotFoundException;
import com.notekeep.model.Note;
import com.notekeep.model.User;
import com.notekeep.payload.request.note.NoteRequest;
import com.notekeep.repository.NoteRepository;
import com.notekeep.repository.UserRepository;
import com.notekeep.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    /**
     * Creates note by data from client
     *
     * @param noteRequest contains necessary data for note
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
                .setBackground(noteRequest.getBackground())
                .setUser(user);
        noteRepository.save(note);
    }
}
