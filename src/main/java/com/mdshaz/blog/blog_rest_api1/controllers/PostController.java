package com.mdshaz.blog.blog_rest_api1.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mdshaz.blog.blog_rest_api1.config.AppConstants;
import com.mdshaz.blog.blog_rest_api1.payloads.PostDto;
import com.mdshaz.blog.blog_rest_api1.payloads.PostResponse;
import com.mdshaz.blog.blog_rest_api1.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class PostController
{
	private PostService postService;
	
	public PostController(PostService postService)
	{
		this.postService = postService;
	}
	@Value("${project.upload.images}")
	String path;

	@PostMapping("/{userId}/categories/{categoryId}/posts")
	ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
			@PathVariable Long userId,
			@PathVariable Long categoryId){
		PostDto savedPost = postService.addPost(postDto, userId,categoryId);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
	}
	@PutMapping("/posts/{postId}")
	ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable Long postId){
		PostDto savedPost = postService.updatePost(postDto,postId);
		return ResponseEntity.status(HttpStatus.OK).body(savedPost);
	}
	@GetMapping("/posts")
	ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer page_number,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE) Integer page_size,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY) String sort_by
	){
		PostResponse posts = postService.getAllPost(page_number,page_size,sort_by);
		return ResponseEntity.ok(posts);
	}
	@GetMapping("/posts/{postId}")
	ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
		PostDto post = postService.getPostById(postId);
		return ResponseEntity.ok(post);
		
	}
	@GetMapping("/{userId}/posts")
	ResponseEntity<PostResponse> getAllPostByUser(
			@PathVariable Long userId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer page_number,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE) Integer page_size,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY) String sort_by
			){
		PostResponse posts = postService.getAllPostByUserId(userId,page_number,page_size,sort_by);
		return ResponseEntity.ok(posts);
		
	}
	@GetMapping("/categories/{categoryId}/posts")
	ResponseEntity<PostResponse> getAllPostByCategory(
			@PathVariable Long categoryId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer page_number,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE) Integer page_size,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY) String sort_by
			){
		PostResponse posts = postService.getPostsByCategory(categoryId,page_number,page_size,sort_by);
		return ResponseEntity.ok(posts);
		
	}
	@DeleteMapping("/posts/{postId}")
	ResponseEntity<?> deletePost(@PathVariable Long postId){
		postService.deletePostById(postId);
		return ResponseEntity.noContent().build();
		
	}
	@GetMapping("/search")
	ResponseEntity<List<PostDto>> searchPosts(@RequestParam(name = "title") String postTitle){
		List<PostDto> posts = postService.searchPosts(postTitle);
		return ResponseEntity.ok(posts);
	}
	@PostMapping("/posts/{id}/image/upload")
	ResponseEntity<PostDto> uploadImage(
			@RequestParam("image") MultipartFile file,
			@PathVariable("id") Long postId
			) throws IOException
	{
		PostDto post = postService.getPostById(postId);
		String imageName= postService.uploadImage(path,postId, file);
		post.setImageUrl(imageName);
		PostDto updatedPost= postService.updatePost(post, postId);
		return ResponseEntity.ok(updatedPost);
		
	}
	
	@GetMapping("/posts/image/{imageName}")
    public ResponseEntity<InputStreamResource> getImageUsingStream(@PathVariable String imageName) throws IOException {
        	InputStreamResource isr = postService.getResource(path, imageName);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust the media type accordingly
                    .body(isr);
    }
	

}
