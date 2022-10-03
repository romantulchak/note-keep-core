package com.notekeep.service.impl;

import com.notekeep.component.Transformer;
import com.notekeep.constant.AppConstant;
import com.notekeep.dto.NoteBackgroundDTO;
import com.notekeep.dto.NoteDTO;
import com.notekeep.exception.label.LabelNotFoundException;
import com.notekeep.exception.note.NoteNotFoundException;
import com.notekeep.exception.user.UserNotFoundException;
import com.notekeep.model.Label;
import com.notekeep.model.Note;
import com.notekeep.model.User;
import com.notekeep.model.enums.BackgroundType;
import com.notekeep.model.enums.NoteBackground;
import com.notekeep.model.enums.NoteColor;
import com.notekeep.payload.request.label.AddLabelToNoteRequest;
import com.notekeep.payload.request.note.ChangeBackgroundRequest;
import com.notekeep.payload.request.note.NoteRequest;
import com.notekeep.projection.NoteWithoutUserProjection;
import com.notekeep.repository.LabelRepository;
import com.notekeep.repository.NoteRepository;
import com.notekeep.repository.UserRepository;
import com.notekeep.service.NoteService;
import com.notekeep.utility.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final Transformer transformer;

    /**
     * Creates note by data from client
     *
     * @param noteRequest    contains necessary data for note
     * @param authentication to get user in system
     */
    @Override
    public NoteDTO create(NoteRequest noteRequest, Authentication authentication) {
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
        return transformer.convertNoteToDTO(noteRepository.save(note));
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
     * Gets all existing backgrounds and colors for note
     *
     * @return {@link NoteBackgroundDTO} with background/color name and its value
     */
    @Override
    public Map<String, List<NoteBackgroundDTO>> getAllBackgroundColors() {
        return Map.of(AppConstant.BACKGROUNDS_KEY_WORD, NoteBackground.getNoteBackgrounds(), AppConstant.COLORS_KEY_WORD, NoteColor.getColors());
    }

    /**
     * Gets user notes (40 elements per page) which not marked and not archived
     *
     * @param page           current page
     * @param authentication to get user email in system
     * @return list {@link NoteDTO} of notes for user
     */
    @Override
    public List<NoteDTO> getNotes(String page, Authentication authentication) {
        int pageNumber = PageableHelper.getPageNumberFromString(page);
        Pageable pageable = PageRequest.of(pageNumber, 40);
        return noteRepository.findNotesByUserEmailAndIsMarkedFalseAndIsArchivedFalseOrderByOrder(authentication.getName(), pageable)
                .getContent()
                .stream()
                .map(transformer::convertNoteToDTO)
                .toList();
    }

    /**
     * @param labelName      for getting notes for label
     * @param page           current page
     * @param authentication to get user email in system
     * @return list of notes filtered by label name
     */
    @Override
    public List<NoteDTO> getNotesByLabelName(String labelName, String page, Authentication authentication) {
        int pageNumber = PageableHelper.getPageNumberFromString(page);
        Pageable pageable = PageRequest.of(pageNumber, 40);
        String userEmail = authentication.getName();
        Label label = labelRepository.findByNameAndUserEmail(labelName, userEmail)
                .orElseThrow(LabelNotFoundException::new);
        Slice<NoteWithoutUserProjection> notesByLabelsContains = noteRepository.findNotesByLabelName(label.getId(), pageable);
        return notesByLabelsContains.getContent()
                .stream()
                .map(transformer::convertNoteToDTO)
                .toList();
    }

    /**
     * Change order for note
     *
     * @param id of note
     * @return current order for note
     */
    @Override
    public int changeOrderForNote(String id) {
        return 0;
    }

    /**
     * Change Note background for note
     *
     * @param changeBackgroundRequest contains info about note, background name and type
     */
    @Override
    public void changeNoteBackground(ChangeBackgroundRequest changeBackgroundRequest) {
        Note note = noteRepository.findById(changeBackgroundRequest.getNoteId())
                .orElseThrow(NoteNotFoundException::new);
        setBackgroundValueByType(note, changeBackgroundRequest.getType(), changeBackgroundRequest.getBackgroundName());
    }

    /**
     * Adds label to note then user can filter by labels
     *
     * @param addLabelToNoteRequest contains note id and label id
     */
    @Override
    public void addLabelToNote(AddLabelToNoteRequest addLabelToNoteRequest) {
        Note note = noteRepository.findById(addLabelToNoteRequest.getNoteId())
                .orElseThrow(NoteNotFoundException::new);
        Label label = labelRepository.findById(addLabelToNoteRequest.getLabelId())
                .orElseThrow(LabelNotFoundException::new);
        if (!note.getLabels().contains(label)) {
            note.getLabels().add(label);
            noteRepository.save(note);
        }
    }

    /**
     * Adds note to archive if its exists for user in system
     *
     * @param id of note
     * @param authentication to get user email in system
     */
    @Override
    public void addNoteToArchive(String id, Authentication authentication) {
        findNoteByIdAndUserIdAndChangeArchived(id, authentication, true);
    }

    /**
     * Removes note from archive if its exists for user in system
     *
     * @param id of note
     * @param authentication to get user email in system
     */
    @Override
    public void removeNoteFromArchive(String id, Authentication authentication) {
        findNoteByIdAndUserIdAndChangeArchived(id, authentication, false);
    }

    /**
     * Gets user notes (40 elements per page) which not marked and archived
     *
     * @param page           current page
     * @param authentication to get user email in system
     * @return list {@link NoteDTO} of notes for user
     */
    @Override
    public List<NoteDTO> getArchivedNotes(String page, Authentication authentication) {
        int pageNumber = PageableHelper.getPageNumberFromString(page);
        Pageable pageable = PageRequest.of(pageNumber, 40);
        return noteRepository.findNotesByIsArchivedTrueAndUserEmail(authentication.getName(), pageable)
                .getContent()
                .stream()
                .map(transformer::convertNoteToDTO)
                .toList();
    }

    /**
     * Toggle note isMarked
     *
     * @param id of note
     */
    @Override
    public void toggleNoteMarked(String id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(NoteNotFoundException::new);
        note.setMarked(!note.isMarked());
        noteRepository.save(note);
    }

    private void findNoteByIdAndUserIdAndChangeArchived(String id, Authentication authentication, boolean isArchived) {
        Note note = noteRepository.findByIdAndUserEmail(id, authentication.getName())
                .orElseThrow(NoteNotFoundException::new);
        note.setArchived(isArchived);
        noteRepository.save(note);
    }

    private void setBackgroundValueByType(Note note, BackgroundType backgroundType, String name) {
        if (backgroundType == BackgroundType.COLOR) {
            String noteColorValue = NoteColor.getNoteColorValueByName(name);
            note.setBackgroundColor(noteColorValue);
        } else if (backgroundType == BackgroundType.IMAGE) {
            String noteBackgroundValue = NoteBackground.getNoteBackgroundByName(name);
            note.setBackgroundImage(noteBackgroundValue);
        }
        noteRepository.save(note);
    }
}
