package com.usmanTech.journalApp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usmanTech.journalApp.Entity.JournalEntry;
import com.usmanTech.journalApp.Entity.Users;
import com.usmanTech.journalApp.Repository.JournalRepository;

@Service
public class JournalService {
	
	@Autowired
	private JournalRepository journalRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public JournalEntry addEntry(JournalEntry entity, String userName) {
		try {
		Users user=userService.findByUserName(userName);
		entity.setDate(LocalDateTime.now());
		JournalEntry savedEntry= journalRepository.save(entity);
		 user.getJournalEntries().add(savedEntry);
		 userService.addUser(user);
		 return savedEntry;	
		}catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("An erroe occured while saving the entry",e);
		}
	}
	public JournalEntry addEntry(JournalEntry entity) {
		return  journalRepository.save(entity);
		 	
	}
	
	public List<JournalEntry> getAllJournalEntriesOfUser(String userName) {
		Users user=userService.findByUserName(userName);
		List<JournalEntry> entity= user.getJournalEntries();
		return entity;		
	}
	public Optional<JournalEntry> findEntryById(ObjectId id) {
		return journalRepository.findById(id);		
	}
	@Transactional
	public boolean deleteEntry(ObjectId id,String userName) {
		boolean removed=false;
		try {
		Users user=userService.findByUserName(userName);
		 removed=user.getJournalEntries().removeIf(i->i.getId().equals(id));
		if(removed) {
		userService.addUser(user);
		 journalRepository.deleteById(id);
		}
		}catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Error occured while deleting the entry",e);
		}
		return removed;
	}
	
	
	

}
