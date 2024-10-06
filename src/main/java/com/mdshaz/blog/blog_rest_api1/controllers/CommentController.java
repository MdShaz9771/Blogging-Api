package com.mdshaz.blog.blog_rest_api1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdshaz.blog.blog_rest_api1.payloads.CommentDto;
import com.mdshaz.blog.blog_rest_api1.payloads.CommentRequestDto;
import com.mdshaz.blog.blog_rest_api1.services.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Comments Api", description = "Endpoints for comment management")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("{userId}/posts/{postId}/comments")
    @Operation(summary = "Add a comment", description = "Adds a new comment to a post")
    ResponseEntity<CommentDto> addComment(@RequestBody CommentRequestDto commentdto, @PathVariable Long userId, @PathVariable Long postId) {
        CommentDto savedComment = commentService.addComment(commentdto, userId, postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @GetMapping("/posts/{postId}/comments")
    @Operation(summary = "Get all comments of a post", description = "Retrieves all comments for a specific post")
    ResponseEntity<List<CommentDto>> getAllCommentsOfPost(@PathVariable Long postId) {
        List<CommentDto> comments = commentService.findAllCommentByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/posts/comments/{commentId}")
    @Operation(summary = "Get a comment by ID", description = "Retrieves a specific comment by ID")
    ResponseEntity<CommentDto> getAllCommentById(@PathVariable Long commentId) {
        CommentDto comment = commentService.findCommentById(commentId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/posts/{postId}/comments/count")
    @Operation(summary = "Get total comments for a post", description = "Retrieves the total number of comments for a specific post")
    ResponseEntity<Long> getTotalComments(@PathVariable Long postId) {
        Long total = commentService.getTotalComments(postId);
        return ResponseEntity.ok(total);
    }

    @DeleteMapping("/posts/comments/{commentId}")
    @Operation(summary = "Remove a comment", description = "Deletes a specific comment by ID")
    ResponseEntity<?> removeComment(@PathVariable Long commentId) {
        commentService.removeComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
