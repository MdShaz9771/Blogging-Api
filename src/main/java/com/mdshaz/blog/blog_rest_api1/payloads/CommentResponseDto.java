package com.mdshaz.blog.blog_rest_api1.payloads;

import java.time.LocalDateTime;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data transfer object representing a comment on a blog post")
public class CommentResponseDto {

    @Schema(description = "Unique ID of the comment", example = "101")
    private Long id;

    @Schema(description = "Content of the comment", example = "This is a great post!")
    private String content;

    @Schema(description = "Timestamp of when the comment was created", example = "2023-10-04T12:45:30")
    private LocalDateTime createdAt;

    @Schema(description = "ID of the post the comment belongs to", example = "201")
    private Long postId;

    @Schema(description = "Details of the user who made the comment")
    private Map<String, String> userDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Map<String, String> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Map<String, String> userDetails) {
        this.userDetails = userDetails;
    }

    public CommentResponseDto() {
    }
}
