package com.mdshaz.blog.blog_rest_api1.payloads;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostDto
{
	

	private long id;
	
	@NotBlank(message = "Title must not be blank")
	@Size(max = 100,message = "Title must be less than 100 characters")
	private String title;
	
	@NotBlank(message = "Content must not be blank")
	@Size(max = 3000,message = "Title must be less than 3000 characters")
	private String content;
	
	private String imageUrl;
	
	private LocalDateTime createdAt;
	
	private Map<String, String> userDetails;
	
	private CategoryDto categorDto;
	
	private Long totalLikes;
	boolean isLikedByCurrentUser;
	
//	private List<String> comments;
	
//	private Long likes;

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

	public CategoryDto getCategoryDto()
	{
		return categorDto;
	}

	public void setCategoryDto(CategoryDto categorDto)
	{
		this.categorDto = categorDto;
	}
	
	

//	public List<String> getComments()
//	{
//		return comments;
//	}
//
//	public void setComments(List<String> comments)
//	{
//		this.comments = comments;
//	}

//	public Long getLikes()
//	{
//		return likes;
//	}
//
//	public void setLikes(Long likes)
//	{
//		this.likes = likes;
//	}

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
