package com.notekeep.repository;

import com.notekeep.model.Note;
import com.notekeep.projection.NoteWithoutUserProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface NoteRepository extends MongoRepository<Note, String> {

    boolean existsByIdAndUserEmail(String id, String userEmail);

    Optional<Note> findByIdAndUserEmail(String id, String email);

    Slice<NoteWithoutUserProjection> findNotesByUserEmailOrderByOrder(String userId, Pageable pageable);

    @Query(value = "{ 'labels.$id': ?0 }", fields = "{'_id': 1}")
    Slice<NoteWithoutUserProjection> findNotesByLabelName(String id, Pageable pageable);
}
