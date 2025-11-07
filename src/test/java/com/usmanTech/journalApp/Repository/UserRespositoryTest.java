package com.usmanTech.journalApp.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRespositoryTest {
@Autowired
private UserRepositoryImpl userRepositoryImpl;

@Test
public void testGetUserForSA() {
	userRepositoryImpl.getUserForSA();
}

}
