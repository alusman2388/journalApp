package com.usmanTech.journalApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usmanTech.journalApp.Entity.Users;
import com.usmanTech.journalApp.Service.UserDetailsServiceImpl;
import com.usmanTech.journalApp.Service.UserService;
import com.usmanTech.journalApp.Utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
	@Autowired
	private UserService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Value("${app.appProfile}")
	private String profile;
	
	@GetMapping("/health-ckeck")
	public String healthCheck() {
		return "API started... with "+profile;
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody Users user) {
		try {
			Users entity=service.addNewUser(user);
			return new ResponseEntity<>(entity,HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>("User already exist",HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/log-in")
	public ResponseEntity<String> logIn(@RequestBody Users user) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
			UserDetails userDetails=userDetailsServiceImpl.loadUserByUsername(user.getUserName());
			String jwt=jwtUtil.generateToken(userDetails.getUsername());
			return new ResponseEntity<>(jwt,HttpStatus.OK);
			
		} catch (Exception e) {
			log.error("Exception occured while creating Authenitcation tokem"+e);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
