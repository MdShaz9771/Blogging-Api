package com.mdshaz.blog.blog_rest_api1.payloads;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentDto
{
	private Long id;
	
	@NotBlank(message = "Content must not be blank")
	@Size(max = 3000,message = "Title must be less than 3000 characters")
	private String content;
	
	private LocalDateTime createdAt;
	private Long postId;
	private Map<String, String> userDetails;
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public LocalDateTime getCreatedAt()
	{
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt)
	{
		this.createdAt = createdAt;
	}
	public Long getPostId()
	{
		return postId;
	}
	public void setPostId(Long postId)
	{
		this.postId = postId;
	}
	public Map<String, String> getUserDetails()
	{
		return userDetails;
	}
	public void setUserDetails(Map<String, String> userDetails)
	{
		this.userDetails = userDetails;
	}
	public CommentDto()
	{
	}
	

}
