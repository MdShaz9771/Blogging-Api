package com.mdshaz.blog.blog_rest_api1.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "This is post reques dto")
public class PostRequestDto
{
	@NotBlank(message = "Title must not be blank")
	@Size(max = 100, message = "Title must be less than 100 characters")
	@Schema(description = "Title of the blog post", example = "Understanding Spring Boot")
	private String title;

	@NotBlank(message = "Content must not be blank")
	@Size(max = 3000, message = "Content must be less than 3000 characters")
	@Schema(description = "Content of the blog post", example = "This post explains how to use Spring Boot...")
	private String content;


	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}


	
}
