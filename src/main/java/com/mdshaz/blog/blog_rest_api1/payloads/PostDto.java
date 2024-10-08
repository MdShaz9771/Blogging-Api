package com.mdshaz.blog.blog_rest_api1.payloads;

import java.time.LocalDateTime;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data transfer object representing a blog post")
public class PostDto
{

	@Schema(description = "Unique ID of the post", example = "1")
	private long id;

	@Schema(description = "Title of the blog post", example = "Understanding Spring Boot")
	private String title;

	@Schema(description = "Content of the blog post", example = "This post explains how to use Spring Boot...")
	private String content;

	@Schema(description = "URL of the post image", example = "http://example.com/image.jpg")
	private String imageUrl;

	@Schema(description = "Timestamp of when the post was created", example = "2023-10-04T12:45:30")
	private LocalDateTime createdAt;

	@Schema(description = "Details of the user who created the post", example = "{\"username\": \"john_doe\", \"email\": \"john@example.com\"}")
	private Map<String, String> userDetails;

	@Schema(description = "Category of the post")
	private CategoryResponseDto categoryDto;

	@Schema(description = "Total number of likes for the post", example = "10")
	private Long totalLikes;

	@Schema(description = "Indicates whether the post is liked by the current user", example = "true")
	private boolean isLikedByCurrentUser;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

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

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt)
	{
		this.createdAt = createdAt;
	}

	public Map<String, String> getUserDetails()
	{
		return userDetails;
	}

	public void setUserDetails(Map<String, String> userDetails)
	{
		this.userDetails = userDetails;
	}

	public CategoryResponseDto getCategoryDto()
	{
		return categoryDto;
	}

	public void setCategoryDto(CategoryResponseDto categoryDto)
	{
		this.categoryDto = categoryDto;
	}

	public Long getTotalLikes()
	{
		return totalLikes;
	}

	public void setTotalLikes(Long totalLikes)
	{
		this.totalLikes = totalLikes;
	}

	public boolean isLikedByCurrentUser()
	{
		return isLikedByCurrentUser;
	}

	public void setLikedByCurrentUser(boolean isLikedByCurrentUser)
	{
		this.isLikedByCurrentUser = isLikedByCurrentUser;
	}

	public PostDto()
	{
	}
}
