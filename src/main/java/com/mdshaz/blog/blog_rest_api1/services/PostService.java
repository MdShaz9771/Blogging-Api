package com.mdshaz.blog.blog_rest_api1.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import com.mdshaz.blog.blog_rest_api1.payloads.PostDto;
import com.mdshaz.blog.blog_rest_api1.payloads.PostResponse;

public interface PostService
{
	 PostDto addPost(PostDto postDto, Long userId, Long categoryId);
	 PostDto updatePost(PostDto postDto,Long postId);//
	 PostDto getPostById(Long postId);
	 void deletePostById(Long postId);
	 PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	 PostResponse getAllPostByUserId(Long userId,Integer pageNumber,Integer pageSize,String sortBy);
	 PostResponse getPostsByCategory(Long categoryId,Integer pageNumber,Integer pageSize,String sortBy);
	 List<PostDto> searchPosts(String title);
	 
	 String uploadImage(String path,Long postId,MultipartFile mFile) throws IOException;
//	 InputStream getResource(String path,String fileName) throws FileNotFoundException;
	 InputStreamResource getResource(String path,String fileName) throws IOException;

}
