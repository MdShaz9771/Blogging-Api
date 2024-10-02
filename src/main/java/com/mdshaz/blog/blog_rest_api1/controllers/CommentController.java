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
import com.mdshaz.blog.blog_rest_api1.services.CommentService;

@RestController
@RequestMapping("/api/users")
public class CommentController
{
	private CommentService commentService;
	
	
	public CommentController(CommentService commentService)
	{
		this.commentService = commentService;
	}


	@PostMapping("{userId}/posts/{postId}/comments")
	ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentdto,@PathVariable Long userId, @PathVariable Long postId){
		CommentDto savedComment = commentService.addComment(commentdto, userId, postId);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
	}
	@GetMapping("/posts/{postId}/comments")
	ResponseEntity<List<CommentDto>> getAllCommentsOfPost(@PathVariable Long postId){
		List<CommentDto> comments = commentService.findAllCommentByPostId(postId);
		return ResponseEntity.ok(comments);
	}
	@GetMapping("/posts/comments/{commentId}")
	ResponseEntity<CommentDto> getAllCommentById(@PathVariable Long commentId){
		CommentDto comment = commentService.findCommentById(commentId);
		return ResponseEntity.ok(comment);
	}
	@GetMapping("/posts/{postId}/comments/count")
	ResponseEntity<Long> getTotalComments(@PathVariable Long postId){
		Long total = commentService.getTotalComments(postId);
		return ResponseEntity.ok(total);
	}
	@DeleteMapping("/posts/comments/{commentId}")
	ResponseEntity<?> removeComment(@PathVariable Long commentId){
		commentService.removeComment(commentId);
		return ResponseEntity.noContent().build();
	}
	

}
