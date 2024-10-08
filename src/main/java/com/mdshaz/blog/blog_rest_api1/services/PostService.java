package com.mdshaz.blog.blog_rest_api1.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.mdshaz.blog.blog_rest_api1.payloads.PostDto;
import com.mdshaz.blog.blog_rest_api1.payloads.PostRequestDto;
import com.mdshaz.blog.blog_rest_api1.payloads.PostResponse;

public interface PostService
{
	 PostDto addPost(PostRequestDto postReqDto, Long userId, Long categoryId);
	 PostDto updatePost(PostRequestDto postReqDto,Long postId,UserDetails userDetails);
	 PostDto updatePost(PostDto postDto,Long postId,UserDetails userDetails);
	 PostDto getPostById(Long postId,UserDetails userDetails);
	 void deletePostById(Long postId,UserDetails userDetails);
	 PostResponse getAllPost(UserDetails userDetails, Integer pageNumber,Integer pageSize,String sortBy);
	 PostResponse getAllPostByUserId(Long userId,UserDetails userDetails, Integer pageNumber,Integer pageSize,String sortBy);
	 PostResponse getPostsByCategory(UserDetails userDetails, Long categoryId,Integer pageNumber,Integer pageSize,String sortBy);
	 List<PostDto> searchPosts(String title);
	 
	 String uploadImage(String path,Long postId,MultipartFile mFile) throws IOException;
//	 InputStream getResource(String path,String fileName) throws FileNotFoundException;
	 InputStreamResource getResource(String path,String fileName) throws IOException;

}
