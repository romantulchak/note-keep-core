package com.notekeep.access;

import com.notekeep.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserNoteAccess {

    private final NoteRepository noteRepository;

    /**
     * Checks if note exists for current user in system by its email
     * and note id
     *
     * @param authentication to get user email in system
     * @param noteId         to check if note exists
     * @return true if note exists for current user otherwise false
     */
    public boolean hasAccess(Authentication authentication, String noteId) {
        return noteRepository.existsByIdAndUserEmail(noteId, authentication.getName());
    }

}
