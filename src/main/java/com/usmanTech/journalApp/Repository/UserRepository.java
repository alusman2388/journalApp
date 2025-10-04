package com.usmanTech.journalApp.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.usmanTech.journalApp.Entity.Users;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
	Users findByUserName(String userName);
	void deleteByUserName(String userName);

}
