package com.usmanTech.journalApp.Service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {
	@Autowired
	private RedisTemplate redisTemplate;
	
	public <T> T get(String city ,Class<T> entityClass) {
		try {
			Object obj=redisTemplate.opsForValue().get(city);
			ObjectMapper mapper=new ObjectMapper();
			return mapper.readValue(obj.toString(), entityClass);			
		} catch (Exception e) {
			log.error("Exception"+e);
			return null;
		}
		
	}
	public <T> void set(String city, Object obj,long ttl) {
		try {
			ObjectMapper mapper=new ObjectMapper();
			String jsonObj=mapper.writeValueAsString(obj);
			redisTemplate.opsForValue().set(city, jsonObj,ttl,TimeUnit.SECONDS);
		} catch (Exception e) {
			log.error("Exception"+e);
		}
		
	}
}
