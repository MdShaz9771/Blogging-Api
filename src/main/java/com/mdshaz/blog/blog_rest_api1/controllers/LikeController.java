package com.mdshaz.blog.blog_rest_api1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdshaz.blog.blog_rest_api1.services.LikeService;

@RestController()
@RequestMapping("/api/users")
public class LikeController
{
	private LikeService likeService;
	
	public LikeController(LikeService likeService)
	{
		this.likeService = likeService;
	}

	@PostMapping("/{userId}/posts/{postId}/like")
	ResponseEntity<String> addLike(@PathVariable Long postId,@PathVariable Long userId){
		String like = likeService.addLike(userId, postId);
		return ResponseEntity.status(HttpStatus.CREATED).body(like);
	}
	@DeleteMapping("/{userId}/posts/{postId}/like")
	ResponseEntity<String> removeLike(@PathVariable Long postId,@PathVariable Long userId){
		String like = likeService.removeLike(userId, postId);
		return ResponseEntity.ok(like);
	}

}
