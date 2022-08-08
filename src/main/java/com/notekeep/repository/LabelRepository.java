package com.notekeep.repository;

import com.notekeep.model.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LabelRepository extends MongoRepository<Label, String> {

    Optional<Label> findByNameAndUserEmail(String name, String email);

    boolean existsByName(String name);
}
