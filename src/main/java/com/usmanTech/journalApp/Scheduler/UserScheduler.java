package com.usmanTech.journalApp.Scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.usmanTech.journalApp.Entity.JournalEntry;
import com.usmanTech.journalApp.Entity.Users;
import com.usmanTech.journalApp.Repository.UserRepositoryImpl;
import com.usmanTech.journalApp.Service.EmailService;
import com.usmanTech.journalApp.Service.SentimentAnalysisService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserScheduler {
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserRepositoryImpl userRepositoryImpl;
	@Autowired
	private SentimentAnalysisService analysisService;
	
	//@Scheduled(cron = "0 9 * * SUN")
	public void fetchUserAndSendSAMail() {
		List<Users> user=userRepositoryImpl.getUserForSA();
		for(Users users:user) {
			List<JournalEntry> journalEntries=users.getJournalEntries();
			List<String> filteredEntries=journalEntries.stream().filter(i->i.getDate().isAfter(LocalDateTime.now().minus(7,ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
			String entry =String.join(" ", filteredEntries);
			String sentiment=analysisService.getSentiment(entry);
			emailService.sendMail(users.getEmail(), "Sentiment of 7 days", sentiment);
		}
	}
//	@Scheduled(cron = "0 */1 * * * ?")
//	public void sendtestingMail() {
//		try {
//			emailService.sendMail("usmanpersonal0@gmail.com", "Just for testing", "hello good afternoon");
//			log.info("Mail send successfully");
//		} catch (Exception e) {
// 		
//	}
}
