package com.usmanTech.journalApp.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.usmanTech.journalApp.Entity.Users;

public class UserRepositoryImpl {
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Users> getUserForSA() {
		Query query=new Query();
	//query.addCriteria(Criteria.where("userName").is("usman"));
		query.addCriteria(Criteria.where("email").exists(true));
		query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
		List<Users> user=mongoTemplate.find(query, Users.class);
		return user;
		
	}
}
