package com.usmanTech.journalApp.api.Response;

import lombok.Data;

@Data
public class PostsResponse {
	private int userId;
	private int id;
	private String title;
	private String body;
}
