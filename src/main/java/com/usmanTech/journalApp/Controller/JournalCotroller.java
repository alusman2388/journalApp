package com.usmanTech.journalApp.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usmanTech.journalApp.Entity.JournalEntry;

@RestController
@RequestMapping("/api")
public class JournalCotroller {
private HashMap<Integer, JournalEntry> journalEntries=new HashMap<Integer, JournalEntry>();

@GetMapping("/get-entries")
public List<JournalEntry> getAll() {
	return new ArrayList<JournalEntry>(journalEntries.values());
}

@PostMapping("/add-entry")
public boolean creatEntry(@RequestBody JournalEntry entity) {
	//journalEntries.put(entity.getId(), entity);
	return true;
	
}
@GetMapping("/id/{id}")
public JournalEntry getJournalEntryById(@PathVariable int id) {
	return journalEntries.get(id);
	
}
@PutMapping("/id/{id}")
public JournalEntry updateEntry(@RequestBody JournalEntry entity,@PathVariable int id) {
	journalEntries.put(id, entity);
	return entity;
	
}
@DeleteMapping("/id/{id}")
public JournalEntry deleteEntry(@PathVariable int id) {
	return journalEntries.remove(id);
}
}
