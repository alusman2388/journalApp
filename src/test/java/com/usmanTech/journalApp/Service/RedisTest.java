package com.usmanTech.journalApp.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
	
	@Autowired
	private RedisTemplate redisTemplate;
	@Disabled
	@Test
	public void rediTest() {
		redisTemplate.opsForValue().set("email", "usman@gmail.com");
		System.out.println(redisTemplate.opsForValue().get("name"));
		
	}

}
