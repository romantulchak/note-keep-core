package com.notekeep.repository;

import com.notekeep.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note, String> {
}
