package com.notekeep.repository;

import com.notekeep.model.Label;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends MongoRepository<Label, String> {

    Optional<Label> findByNameAndUserEmail(String name, String email);

    List<Label> findAllByUserEmail(String email);

    boolean existsByNameAndUserEmail(String name, String userEmail);

    void deleteByNameAndUserEmail(String name, String userEmail);
}
