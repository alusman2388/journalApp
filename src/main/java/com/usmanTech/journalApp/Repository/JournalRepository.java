package com.usmanTech.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.usmanTech.journalApp.Entity.JournalEntry;

@Repository
public interface JournalRepository extends MongoRepository<JournalEntry, ObjectId> {

}
