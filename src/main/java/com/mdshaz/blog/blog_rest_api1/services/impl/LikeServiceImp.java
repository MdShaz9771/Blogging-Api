package com.mdshaz.blog.blog_rest_api1.services.impl;

import org.springframework.stereotype.Service;

import com.mdshaz.blog.blog_rest_api1.entity.Like;
import com.mdshaz.blog.blog_rest_api1.entity.Post;
import com.mdshaz.blog.blog_rest_api1.entity.User;
import com.mdshaz.blog.blog_rest_api1.exceptions.ResourceNotFoundException;
import com.mdshaz.blog.blog_rest_api1.exceptions.UserNotFoundException;
import com.mdshaz.blog.blog_rest_api1.repositories.LikeRepo;
import com.mdshaz.blog.blog_rest_api1.repositories.PostRepo;
import com.mdshaz.blog.blog_rest_api1.repositories.UserRepo;
import com.mdshaz.blog.blog_rest_api1.services.LikeService;

@Service
public class LikeServiceImp implements LikeService
{
	private LikeRepo likeRepo;
	private PostRepo postRepo;
	private UserRepo userRepo;
	

	public LikeServiceImp(LikeRepo likeRepo, PostRepo postRepo, UserRepo userRepo)
	{
		this.likeRepo = likeRepo;
		this.postRepo = postRepo;
		this.userRepo = userRepo;
	}

	@Override
	public String addLike(Long userId, Long postId)
	{
		User user = userRepo.findById(userId)
				.orElseThrow(()->new UserNotFoundException("No user found with this ID: "+userId));
		Post post = postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("No Post found with this ID: "+postId));
		if(likeRepo.existsByUserAndPost(user, post))
			throw new IllegalArgumentException("User already liked this post");
		Like like = new Like(user,post);
		
		likeRepo.save(like);
		
		return "Post Liked";
	}

	@Override
	public String removeLike(Long userId, Long postId)
	{
		User user = userRepo.findById(userId)
				.orElseThrow(()->new UserNotFoundException("No user found with this ID: "+userId));
		Post post = postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("No Post found with this ID: "+postId));
		if(!likeRepo.existsByUserAndPost(user, post))
			throw new IllegalArgumentException("Post is not liked, You can not remove like");
		Like like = likeRepo.findByUserAndPost(user, post)
				.orElseThrow(()->new ResourceNotFoundException("No like found"));
		likeRepo.delete(like);
		return "Like removed";
	}

	@Override
	public long totalLikesOnPost(Long postId)
	{
		Post post = postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("No Post found with this ID: "+postId));
		Long totalLikes = likeRepo.countByPost(post);
		return totalLikes;
	}

	@Override
	public boolean likeExist(User user, Post post)
	{
		
		return likeRepo.existsByUserAndPost(user, post);
	}

}
