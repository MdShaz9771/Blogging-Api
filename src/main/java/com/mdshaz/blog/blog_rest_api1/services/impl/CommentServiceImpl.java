package com.mdshaz.blog.blog_rest_api1.services.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mdshaz.blog.blog_rest_api1.entity.Comment;
import com.mdshaz.blog.blog_rest_api1.entity.Post;
import com.mdshaz.blog.blog_rest_api1.entity.User;
import com.mdshaz.blog.blog_rest_api1.exceptions.ResourceNotFoundException;
import com.mdshaz.blog.blog_rest_api1.exceptions.UserNotFoundException;
import com.mdshaz.blog.blog_rest_api1.payloads.CommentDto;
import com.mdshaz.blog.blog_rest_api1.repositories.CommentRepo;
import com.mdshaz.blog.blog_rest_api1.repositories.PostRepo;
import com.mdshaz.blog.blog_rest_api1.repositories.UserRepo;
import com.mdshaz.blog.blog_rest_api1.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService
{
	private CommentRepo commentRepo;
	private PostRepo postRepo;
	private UserRepo userRepo;
	private ModelMapper modelMapper;
	

	public CommentServiceImpl(CommentRepo commentRepo,PostRepo postRepo,UserRepo userRepo,ModelMapper modelMapper)
	{
		this.commentRepo = commentRepo;
		this.postRepo = postRepo;
		this.userRepo = userRepo;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public CommentDto addComment(CommentDto commentDto, Long userId, Long postId)
	{
		User user = userRepo.findById(userId)
				.orElseThrow(()->new UserNotFoundException("No user found"));
		Post post = postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("No post found"));
		
		Comment comment = commentDtoToComment(commentDto);
		comment.setUser(user);
		comment.setPost(post);
		comment.setCreatedAt(LocalDateTime.now());
		Comment savedComment= commentRepo.save(comment);
		
		return commentToCommentDto(savedComment);
	}

	@Override
	public List<CommentDto> findAllCommentByPostId(Long postId)
	{
		Post post = postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("No post found"));
		Set<Comment> comments = post.getComments();
		if(comments.isEmpty())
			throw new ResourceNotFoundException("No comment found for post id: "+postId);
		List<CommentDto> commentDtos = comments.stream().map(this::commentToCommentDto).toList();
		return commentDtos;
	}

	@Override
	public CommentDto findCommentById(Long commentId)
	{
		Comment commet = commentRepo.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("No comment found for this post id: "+commentId));
		return commentToCommentDto(commet);
	}

	@Override
	public Long getTotalComments(Long postId)
	{
		Post post = postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("No post found"));
		Long totalComments = commentRepo.countByPost(post);
		return totalComments;
	}
	
	public CommentDto commentToCommentDto(Comment comment) {
		CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
		Map<String, String> userDetails = new HashMap<String, String>();
		userDetails.put("Id: ", comment.getUser().getId().toString());
		userDetails.put("Name", comment.getUser().getName());
		
		commentDto.setPostId(comment.getPost().getId());
		commentDto.setUserDetails(userDetails);
		
		return commentDto;
	}
	public Comment commentDtoToComment(CommentDto commentDto) {
		Comment comment = modelMapper.map(commentDto, Comment.class);
		return comment;
	}

	@Override
	public void removeComment(Long commentId)
	{
		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("No comment found for this ID: "+commentId));
		commentRepo.delete(comment);
		
	}

	

}
