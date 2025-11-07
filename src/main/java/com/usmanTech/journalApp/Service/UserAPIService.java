package com.usmanTech.journalApp.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usmanTech.journalApp.api.Response.PostsResponse;


@Service
public class UserAPIService {
	@Value("${user.api.url}")
	private String API;
	//private static final String API="https://jsonplaceholder.typicode.com/posts";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<PostsResponse> getAllPosts() {
		try {
			ResponseEntity<PostsResponse[]> response=restTemplate.exchange(API, HttpMethod.GET, null,PostsResponse[].class);
			return Arrays.asList(response.getBody());
		}
		catch (Exception e) {
			throw new RuntimeException("Error in call api: "+e.getMessage());
		}	
	}
	public PostsResponse getPostById(int id) {
		try {
			String finalAPI=API+"/"+id;
			ResponseEntity<PostsResponse> response=restTemplate.exchange(finalAPI, HttpMethod.GET, null, PostsResponse.class);	
		return response.getBody();	
		}catch (Exception e) {
			throw new RuntimeException("Error in calling api"+e.getMessage());
		}
		
	}
}
