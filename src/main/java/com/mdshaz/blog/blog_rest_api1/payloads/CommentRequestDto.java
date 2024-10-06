package com.mdshaz.blog.blog_rest_api1.payloads;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentRequestDto
{
	@NotBlank(message = "Content must not be blank")
    @Size(max = 3000, message = "Content must be less than 3000 characters")
    @Schema(description = "Content of the comment", example = "This is a great post!", required = true)
    private String content;

    @Schema(description = "Timestamp of when the comment was created", example = "2023-10-04T12:45:30")
    private LocalDateTime createdAt;

    @Schema(description = "ID of the post the comment belongs to", example = "201")
    private Long postId;

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
    
    

}
