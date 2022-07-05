package com.notekeep.repository;

import com.notekeep.model.Note;
import com.notekeep.projection.NoteWithoutUserProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {

    boolean existsByIdAndUserEmail(String id, String userEmail);

    Slice<NoteWithoutUserProjection> findNotesByUserEmailOrderByOrder(String userId, Pageable pageable);
}
