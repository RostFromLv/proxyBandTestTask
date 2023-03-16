package com.test.proxybandtesttask.service.repository;

import com.test.proxybandtesttask.domain.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note,String> {
}
