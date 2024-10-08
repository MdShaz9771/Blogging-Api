package com.mdshaz.blog.blog_rest_api1.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.mdshaz.blog.blog_rest_api1.payloads.PostRequestDto;
import com.mdshaz.blog.blog_rest_api1.payloads.PostResponse;
import com.mdshaz.blog.blog_rest_api1.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Posts Api", description = "Endpoints for post management")
public class PostController {

    @Value("${project.upload.images}")
    String path;

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{userId}/categories/{categoryId}/posts")
    @Operation(summary = "Create a post", description = "Adds a new post for a specific user and category")
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody PostRequestDto postReqDto,
            @PathVariable Long userId,
            @PathVariable Long categoryId) {
        PostDto savedPost = postService.addPost(postReqDto, userId, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @PutMapping("/posts/{postId}")
    @Operation(summary = "Update a post", description = "Updates an existing post")
    public ResponseEntity<PostDto> updatePost(
            @Valid @RequestBody PostRequestDto postReqDto,
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long postId) {
        PostDto savedPost = postService.updatePost(postReqDto, postId,userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(savedPost);
    }

    @GetMapping("/posts")
    @Operation(summary = "Get all posts", description = "Retrieves a list of all posts")
    public ResponseEntity<PostResponse> getAllPosts(
    		@AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer page_number,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer page_size,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY) String sort_by) {
        PostResponse posts = postService.getAllPost(userDetails,page_number, page_size, sort_by);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{postId}")
    @Operation(summary = "Get a post", description = "Retrieves a specific post by ID")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId,@AuthenticationPrincipal UserDetails userDetails) {
        PostDto post = postService.getPostById(postId,userDetails);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/{userId}/posts")
    @Operation(summary = "Get all posts by user", description = "Retrieves all posts for a specific user")
    public ResponseEntity<PostResponse> getAllPostByUser(
            @PathVariable Long userId,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer page_number,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer page_size,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY) String sort_by) {
        PostResponse posts = postService.getAllPostByUserId(userId,userDetails, page_number, page_size, sort_by);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/categories/{categoryId}/posts")
    @Operation(summary = "Get all posts by category", description = "Retrieves all posts for a specific category")
    public ResponseEntity<PostResponse> getAllPostByCategory(
            @PathVariable Long categoryId,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer page_number,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer page_size,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY) String sort_by) {
        PostResponse posts = postService.getPostsByCategory(userDetails,categoryId, page_number, page_size, sort_by);
        return ResponseEntity.ok(posts);
    }
    @DeleteMapping("/posts/{postId}")
    @Operation(summary = "Delete a post", description = "Deletes a specific post by ID")
    public ResponseEntity<?> deletePost(@PathVariable Long postId,@AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePostById(postId,userDetails);
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search posts", description = "Searches for posts by title")
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam(name = "title") String postTitle) {
        List<PostDto> posts = postService.searchPosts(postTitle);
        return ResponseEntity.ok(posts);
    }

    @PostMapping(value = "/posts/{id}/image/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload an image", description = "Uploads an image for a specific post")
    public ResponseEntity<PostDto> uploadImage(
            @RequestParam("image") MultipartFile file,
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("id") Long postId) throws IOException {
        PostDto post = postService.getPostById(postId,userDetails);
        String imageName = postService.uploadImage(path, postId, file);
        post.setImageUrl(imageName);
        PostDto updatedPost = postService.updatePost(post, postId,userDetails);
        return ResponseEntity.ok(updatedPost);
    }

    @GetMapping("/posts/image/{imageName}")
    @Operation(summary = "Get post image", description = "Retrieves an image by its name")
    public ResponseEntity<InputStreamResource> getImageUsingStream(@PathVariable String imageName) throws IOException {
        InputStreamResource isr = postService.getResource(path, imageName);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(isr);
    }
}
