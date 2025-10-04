package com.usmanTech.journalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usmanTech.journalApp.Entity.Users;
import com.usmanTech.journalApp.Service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	@Autowired
	private UserService service;
	
	@Value("${app.appProfile}")
	private String profile;
	
	@GetMapping("/health-ckeck")
	public String healthCheck() {
		return "API started... with "+profile;
	}
	
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody Users user) {
		try {
			Users entity=service.addNewUser(user);
			return new ResponseEntity<>(entity,HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>("User already exist",HttpStatus.BAD_REQUEST);
		}
	}

}
