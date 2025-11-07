package com.usmanTech.journalApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
	 @Bean
	    public RedisConnectionFactory redisConnectionFactory() {
	        // Create a Lettuce connection to localhost:6379 (default)
	        return new LettuceConnectionFactory("localhost", 6379);
	    }
	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate redisTemplate=new RedisTemplate();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setKeySerializer(new  StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
		
	}

}
