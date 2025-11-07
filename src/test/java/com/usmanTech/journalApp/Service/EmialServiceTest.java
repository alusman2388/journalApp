package com.usmanTech.journalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.usmanTech.journalApp.JournalApplication;

@SpringBootTest(classes = JournalApplication.class)
public class EmialServiceTest {
	@Autowired
	private EmailService emailService;
	@Test
	public void emialTest() {
		emailService.sendMail("usmanpersonal0@gmail.com", "This is emial testing", "yes this emial testing so that can be tested");
	}

}
