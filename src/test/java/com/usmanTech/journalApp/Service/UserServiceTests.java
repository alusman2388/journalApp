package com.usmanTech.journalApp.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.usmanTech.journalApp.JournalApplication;
import com.usmanTech.journalApp.Entity.Users;
import com.usmanTech.journalApp.Repository.UserRepository;

@SpringBootTest(classes = JournalApplication.class)
@Disabled
public class UserServiceTests {
	@Autowired
	private UserRepository userRepo;
	@Disabled
	@Test
	public void testFindByUserName() {
		//assertNotNull(userRepo.findByUserName("usman"));
		//assertEquals(4, 2+2);
		Users user=userRepo.findByUserName("usman");
		assertTrue(!user.getJournalEntries().isEmpty());
	}
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"10,2,12",
		"3,3,9"
	})
	
	public void test(int a,int b,int expected) {
		assertEquals(expected, a+b);
		
	}
	@Disabled
	@ParameterizedTest
	@ValueSource(strings = {
		"usman",
		"sam",
		"ruman"
	})
	public void testFindByusername(String name) {
		assertNotNull(userRepo.findByUserName(name),"failed for: "+name);
	}
	

}
