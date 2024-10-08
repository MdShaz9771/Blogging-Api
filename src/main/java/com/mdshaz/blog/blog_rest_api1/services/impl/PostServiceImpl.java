package com.mdshaz.blog.blog_rest_api1.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mdshaz.blog.blog_rest_api1.entity.Category;
import com.mdshaz.blog.blog_rest_api1.entity.Post;
import com.mdshaz.blog.blog_rest_api1.entity.User;
import com.mdshaz.blog.blog_rest_api1.exceptions.ResourceNotFoundException;
import com.mdshaz.blog.blog_rest_api1.exceptions.UserNotFoundException;
import com.mdshaz.blog.blog_rest_api1.payloads.CategoryResponseDto;
import com.mdshaz.blog.blog_rest_api1.payloads.PostDto;
import com.mdshaz.blog.blog_rest_api1.payloads.PostRequestDto;
import com.mdshaz.blog.blog_rest_api1.payloads.PostResponse;
import com.mdshaz.blog.blog_rest_api1.repositories.CategoryRepo;
import com.mdshaz.blog.blog_rest_api1.repositories.LikeRepo;
import com.mdshaz.blog.blog_rest_api1.repositories.PostRepo;
import com.mdshaz.blog.blog_rest_api1.repositories.UserRepo;
import com.mdshaz.blog.blog_rest_api1.services.CategoryService;
import com.mdshaz.blog.blog_rest_api1.services.PostService;
import com.mdshaz.blog.blog_rest_api1.services.UserService;


@Service
public class PostServiceImpl implements PostService
{
	private ModelMapper modelMapper;
	private UserRepo userRepo;
	private CategoryRepo categoryRepo;
	private PostRepo postRepo;
	private LikeRepo likeRepo;
	
	
	public  PostServiceImpl(ModelMapper modelMapper, 
			UserRepo userRepo, 
			CategoryRepo categoryRepo,
			PostRepo postRepo,
			UserService userService,
			CategoryService categoryService,
			LikeRepo likeRepo)
	{
		this.modelMapper = modelMapper;
		this.userRepo = userRepo;
		this.categoryRepo = categoryRepo;
		this.postRepo = postRepo;
		this.likeRepo = likeRepo;
	}
	
	

	@Override
	public PostDto addPost(PostRequestDto postReqDto, Long userId,Long categoryId)
	{
		PostDto postDto = modelMapper.map(postReqDto, PostDto.class);
		Category category= categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Select a valid category"));
		User user = userRepo.findById(userId)
				.orElseThrow(()->new UserNotFoundException("No user found with id: "+userId));
		Post post = postDtoToPost(postDto);
		post.setCategory(category);
		post.setUser(user);
		post.setCreatedAt(LocalDateTime.now());
		post.setImageUrl("default.png");
		Post savedPost = postRepo.save(post);
		PostDto savedPostDto = postToPostDto(savedPost);
		savedPostDto.setLikedByCurrentUser(false);
		savedPostDto.setTotalLikes(0l);
		return savedPostDto;
	}
	 @Override
	    public PostDto updatePost(PostRequestDto postReqDto,Long postId, UserDetails userDetails) 
	 {
		 	PostDto postDto = modelMapper.map(postReqDto, PostDto.class);
	        Post post = postRepo.findById(postId)
	                .orElseThrow(() -> new ResourceNotFoundException("No post found with this ID"));
	        if(!userDetails.getUsername().equalsIgnoreCase(post.getUser().getEmail()) && !hasRole("ROLE_ADMIN",userDetails) )
	        	throw new AccessDeniedException("You are only allowed to edit own post.");

	        post.setTitle(postDto.getTitle());
	        post.setContent(postDto.getContent());
	        post.setImageUrl(postDto.getImageUrl());

	        Post updatedPost = postRepo.save(post);
	        PostDto updatedPostDto = postToPostDto(updatedPost);
	        updatedPostDto.setTotalLikes(likeRepo.countByPost(updatedPost));
	        return updatedPostDto;
	    }
	 @Override
		public PostDto updatePost(PostDto postDto, Long postId, UserDetails userDetails)
		{
		 	Post post = postRepo.findById(postId)
	                .orElseThrow(() -> new ResourceNotFoundException("No post found with this ID"));
		 	if(!userDetails.getUsername().equalsIgnoreCase(post.getUser().getEmail()) && !hasRole("ROLE_ADMIN",userDetails) )
	        	throw new AccessDeniedException("You are only allowed to edit own post.");

	        post.setTitle(postDto.getTitle());
	        post.setContent(postDto.getContent());

	        Post updatedPost = postRepo.save(post);
	        PostDto updatedPostDto = postToPostDto(updatedPost);
	        updatedPostDto.setTotalLikes(likeRepo.countByPost(updatedPost));
	        return updatedPostDto;
		}

	

	@Override
	public PostDto getPostById(Long postId,UserDetails userDetails)
	{
		
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("No post found with this ID"));
		PostDto postDto=postToPostDto(post);
		if(userDetails !=null) {
			User user = userRepo.findByEmail(userDetails.getUsername())
					.orElseThrow(()->new UserNotFoundException("No user found"));
			boolean liked = likeRepo.existsByUserAndPost(user, post);
			postDto.setLikedByCurrentUser(liked);
		}
		postDto.setTotalLikes(likeRepo.countByPost(post));
		return postDto;
	}



	@Override
	public void deletePostById(Long postId, UserDetails userDetails)
	{
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("No post found with this ID"));
		if(!userDetails.getUsername().equalsIgnoreCase(post.getUser().getEmail()) && !hasRole("ROLE_ADMIN",userDetails) )
        	throw new AccessDeniedException("You are only allowed to delete your own post.");
		postRepo.delete(post);
		
	}



	@Override
	public PostResponse getAllPost(UserDetails userDetails,Integer pageNumber,Integer pageSize,String sortBy)
	{
		Pageable page = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		
		Page<Post> pagePosts = postRepo.findAll(page);
		if(pagePosts.isEmpty())
			throw new ResourceNotFoundException("No post found");
		List<PostDto> postDtos = pagePosts.stream().map(post -> {
	        PostDto postDto = postToPostDto(post);
	        long totalLikes = likeRepo.countByPost(post);
	        postDto.setTotalLikes(totalLikes);
	        if(userDetails !=null) {
				User currentUser = userRepo.findByEmail(userDetails.getUsername())
						.orElseThrow(()->new UserNotFoundException("No user found"));
				boolean liked = likeRepo.existsByUserAndPost(currentUser, post);
				postDto.setLikedByCurrentUser(liked);
			}
	        return postDto;
	    }).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setTotalRecords(pagePosts.getTotalElements());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse;
	}



	@Override
	public PostResponse getAllPostByUserId(Long userId,UserDetails userDetails, Integer pageNumber,Integer pageSize,String sortBy)
	{
		Pageable page = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		User user = userRepo.findById(userId)
				.orElseThrow(()->new UserNotFoundException("No user found with this id"));
		Page<Post> pagePosts = postRepo.findByUser(user,page);
		if(pagePosts.isEmpty())
			throw new ResourceNotFoundException("No post found for this user");
		List<PostDto> postDtos = pagePosts.stream().map(post -> {
	        PostDto postDto = postToPostDto(post);
	        long totalLikes = likeRepo.countByPost(post);
	        postDto.setTotalLikes(totalLikes);
	        if(userDetails !=null) {
				User currentUser = userRepo.findByEmail(userDetails.getUsername())
						.orElseThrow(()->new UserNotFoundException("No user found"));
				boolean liked = likeRepo.existsByUserAndPost(currentUser, post);
				postDto.setLikedByCurrentUser(liked);
			}
	        return postDto;
	    }).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setTotalRecords(pagePosts.getTotalElements());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse;
	}




	@Override
	public PostResponse getPostsByCategory(UserDetails userDetails, Long categoryId,Integer pageNumber,Integer pageSize,String sortBy)
	{
		Pageable page = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("No category found for this category ID: "+categoryId));
		Page<Post> pagePosts = postRepo.findByCategory(category,page);
		if(pagePosts.isEmpty())
			throw new ResourceNotFoundException("No post found");
		List<PostDto> postDtos= pagePosts.stream().map(post -> {
	        PostDto postDto = postToPostDto(post);
	        long totalLikes = likeRepo.countByPost(post);
	        postDto.setTotalLikes(totalLikes);
	        if(userDetails !=null) {
				User currentUser = userRepo.findByEmail(userDetails.getUsername())
						.orElseThrow(()->new UserNotFoundException("No user found"));
				boolean liked = likeRepo.existsByUserAndPost(currentUser, post);
				postDto.setLikedByCurrentUser(liked);
			}
	        return postDto;
	    }).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setTotalRecords(pagePosts.getTotalElements());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse;
	}


	
	public PostDto postToPostDto(Post post) {
		Map<String, String> userDetails=new LinkedHashMap<String, String>();
		userDetails.put("ID", post.getUser().getId().toString());
		userDetails.put("Name", post.getUser().getName());
		userDetails.put("Email", post.getUser().getEmail());
		PostDto postDto = modelMapper.map(post, PostDto.class);
		postDto.setCategoryDto(modelMapper.map(post.getCategory(),CategoryResponseDto.class ));
		postDto.setUserDetails(userDetails);
		return postDto;
		
	}
	public Post postDtoToPost(PostDto postDto) {
		Post post = modelMapper.map(postDto, Post.class);
		return post;
		
	}

	@Override
	public List<PostDto> searchPosts(String title)
	{
		List<Post> posts = postRepo.findByTitleContainingIgnoreCase(title);
		if(posts.isEmpty())
			throw new ResourceNotFoundException("No post found with this title");
		List<PostDto> postDto= posts.stream().map(this::postToPostDto).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public String uploadImage(String path,Long postId, MultipartFile mFile) throws IOException
	{
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post not found"));
		
		if (post.getImageUrl() != null) {
            Path oldFilePath = Paths.get(path).resolve(post.getImageUrl()).normalize();
            Files.deleteIfExists(oldFilePath); // Delete the old image file
            System.out.println("deleleted: "+oldFilePath);
        }
		String originalName = mFile.getOriginalFilename();
		String randomId = UUID.randomUUID().toString();
		String fileName = randomId.concat(originalName.substring(originalName.lastIndexOf('.')));
		
		File file = new File(path);
		if(!file.exists())
			file.mkdir();
			Files.copy(mFile.getInputStream(), Paths.get(path, fileName));
		
		return fileName;
	}



	@Override
	public InputStreamResource getResource(String path, String fileName) throws IOException
	{
		Path filePath = Paths.get(path).resolve(fileName).normalize();
        InputStream inputStream = Files.newInputStream(filePath);
        InputStreamResource resource = new InputStreamResource(inputStream);

		return resource;
	}
	
	private boolean hasRole(String role,UserDetails userDetails) {
		return userDetails.getAuthorities().stream().anyMatch(e->e.getAuthority().equals(role));
	}
	
}
