package com.usmanTech.journalApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usmanTech.journalApp.api.Response.WeatherResponse;
@Service
public class WeatherService {
	private static final String apiKey="5d449ffea20f2004389b769250a679f7";
	private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RedisService redisService;
	
	public WeatherResponse getWeather(String city) {
		WeatherResponse weatherResponse=redisService.get("weather_of_"+city, WeatherResponse.class);
		if(weatherResponse !=null) {
			return weatherResponse;
		}
		else {
			String finalAPI=API.replace("CITY", city).replace("API_KEY", apiKey);
			/*after this we need to hit this api by code that can be done by
			RestTemplete class this will process the http request and return a response */
			ResponseEntity<WeatherResponse> response=restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
			WeatherResponse body=response.getBody();
			if(body !=null) {
				redisService.set("weather_of_"+city, body, 300l);
			}
			return body;	
		}
		
	}
}
