package com.usmanTech.journalApp.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
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
import org.springframework.web.bind.annotation.RestController;

import com.usmanTech.journalApp.Entity.JournalEntry;
import com.usmanTech.journalApp.Entity.Users;
import com.usmanTech.journalApp.Service.JournalService;
import com.usmanTech.journalApp.Service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalCotrollerV2 {
	@Autowired
	private JournalService journalService;
	
	@Autowired
	private UserService userService;
	
@GetMapping()
public ResponseEntity<?> getAllJournalEntriesOfUser() { 
	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	String userName=authentication.getName();
	List<JournalEntry>entity=journalService.getAllJournalEntriesOfUser(userName);
	if(entity!=null && !entity.isEmpty()) {
		return new ResponseEntity<>(entity,HttpStatus.OK);
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

@PostMapping()
public ResponseEntity<JournalEntry> creatEntry(@RequestBody JournalEntry entity) {
	try {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		journalService.addEntry(entity, userName);
		return new ResponseEntity<>(entity,HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
}
@GetMapping("/id/{id}")
public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	String userName=authentication.getName();
	Users user=userService.findByUserName(userName);
	List<JournalEntry> list=user.getJournalEntries().stream().filter(i->i.getId().equals(id)).collect(Collectors.toList());
	 if(!list.isEmpty()) {
		 Optional<JournalEntry> entity=journalService.findEntryById(id);
	 if(entity.isPresent()) {
		 return new ResponseEntity<>(entity.get(), HttpStatus.OK);
	 }
	 }
	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
}
@PutMapping("/id/{id}")
public ResponseEntity<?> updateEntry(@RequestBody JournalEntry entity,@PathVariable ObjectId id) {
	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	String userName=authentication.getName();
	Users user=userService.findByUserName(userName);
	List<JournalEntry> list=user.getJournalEntries().stream().filter(i->i.getId().equals(id)).collect(Collectors.toList());
	if(!list.isEmpty()) {
		 Optional<JournalEntry> journalEntry=journalService.findEntryById(id);
		 if(journalEntry.isPresent()) {
			 JournalEntry old=journalEntry.get();
					old.setTitle(entity.getTitle()!=null && !entity.getTitle().equals("")?entity.getTitle():old.getTitle());
					old.setContent(entity.getContent()!=null && !entity.getContent().equals("")?entity.getContent():old.getContent());
					old.setDate(LocalDateTime.now());
					journalService.addEntry(old);
					return new ResponseEntity<>(old,HttpStatus.OK);
		 }
	}
	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
}
@DeleteMapping("/id/{id}")	
public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id) {
	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	String userName=authentication.getName();
	boolean removed=journalService.deleteEntry(id,userName);
	if(removed) {
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
}
