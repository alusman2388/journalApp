package com.usmanTech.journalApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usmanTech.journalApp.Entity.Users;
import com.usmanTech.journalApp.Repository.UserRepository;
import com.usmanTech.journalApp.Service.EmailService;
import com.usmanTech.journalApp.Service.UserAPIService;
import com.usmanTech.journalApp.Service.UserService;
import com.usmanTech.journalApp.Service.WeatherService;
import com.usmanTech.journalApp.api.Response.PostsResponse;
import com.usmanTech.journalApp.api.Response.WeatherResponse;

@RestController
@RequestMapping("/user")
public class UserCotroller {
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WeatherService weatherService;
	@Autowired
	private UserAPIService userAPIService;
	@Autowired
	private EmailService emailService;

	@PutMapping
	public String updateUser(@RequestBody Users user) {
		Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
		Users oldUser=service.findByUserName(username);
			oldUser.setUserName(user.getUserName());
			oldUser.setPassword(user.getPassword());
			service.addNewUser(oldUser);  
		return "Updated Successfully";
	}
	@DeleteMapping
	public ResponseEntity<?> name() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		userRepository.deleteByUserName(authentication.getName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("/weather")
	public ResponseEntity<?> getWeather() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		WeatherResponse weatherRes=weatherService.getWeather("Mumbai");
		String weather="";
		if(weatherRes!=null) {
			weather=", weather feels like "+weatherRes.getCurrent().getFeelslike();
		}
		
		return new ResponseEntity<>("hi "+ auth.getName()+ weather, HttpStatus.OK);
		
	}
	@GetMapping("/posts")
	public ResponseEntity<?> getUserData(){
		List<PostsResponse> response=userAPIService.getAllPosts();
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostsResponse> getPostByIs(@PathVariable int id){
		PostsResponse response=userAPIService.getPostById(id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@PostMapping("/mail")
	public void sendMail(@RequestParam String to,@RequestParam String subject,@RequestParam String body) {
		emailService.sendMail(to, subject, body);
	}
	
	
	
	
	
}
