package com.notekeep.repository;

import com.notekeep.model.Label;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends MongoRepository<Label, String> {

    @Query(value = "{'name': ?0, 'user.email': ?1 }", fields = "{'_id': 1}")
    Optional<Label> findByNameAndUserEmail(String name, String email);

    List<Label> findAllByUserEmail(String email);

    boolean existsByNameAndUserEmail(String name, String userEmail);

    void deleteByNameAndUserEmail(String name, String userEmail);
}
