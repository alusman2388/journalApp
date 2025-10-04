package com.usmanTech.journalApp.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.TargetClassAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usmanTech.journalApp.Entity.Users;
import com.usmanTech.journalApp.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	private static final PasswordEncoder PASSWORD_ENCODER=new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository userRepo;
	
	public Users addNewUser(Users user) {
		try {
			user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER"));
			return userRepo.save(user);
		}
		catch (Exception e) { 
			log.error("Its a error");
			log.warn("Its a warning");
			log.info("Its a info");
			log.debug("Its a debug");
			log.trace("Its a trace");

			throw new RuntimeException("Failed to add user",e);
		}
		
		
	}
	public Users addNewAdmin(Users user) {
		user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER","ADMIN"));
		return userRepo.save(user);
		
	}
	public void addUser(Users user) {
		userRepo.save(user);
	}
	
	public List<Users> getAllUsers() {
		return userRepo.findAll();
		
	}
	public Users findByUserName(String userName) {
		return userRepo.findByUserName(userName);
		
	}
	public Optional<Users> getUserById(ObjectId id) {
		return userRepo.findById(id);		
	}
	public void deleteUser(ObjectId id) {
		 userRepo.deleteById(id);
		 
		 
	}
	

}
