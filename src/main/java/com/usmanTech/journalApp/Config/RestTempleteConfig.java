package com.usmanTech.journalApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempleteConfig {
	@Bean
	public RestTemplate restTemplete() {
		return new RestTemplate();
	}

}
