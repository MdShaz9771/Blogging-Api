package com.mdshaz.blog.blog_rest_api1.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mdshaz.blog.blog_rest_api1.entity.Role;
import com.mdshaz.blog.blog_rest_api1.entity.User;
import com.mdshaz.blog.blog_rest_api1.exceptions.EmailAlreadyExistException;
import com.mdshaz.blog.blog_rest_api1.exceptions.UserNotFoundException;
import com.mdshaz.blog.blog_rest_api1.payloads.UserResponseDto;
import com.mdshaz.blog.blog_rest_api1.payloads.UserProfile;
import com.mdshaz.blog.blog_rest_api1.payloads.UserRequestDto;
import com.mdshaz.blog.blog_rest_api1.repositories.RoleRepo;
import com.mdshaz.blog.blog_rest_api1.repositories.UserRepo;
import com.mdshaz.blog.blog_rest_api1.services.UserService;

@Service
public class UserServiceImp implements UserService
{
	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;

	public UserServiceImp(UserRepo userRepo,RoleRepo roleRepo,
			ModelMapper modelMapper,PasswordEncoder passwordEncoder)
	{
		this.userRepo = userRepo;
		this.roleRepo=roleRepo;
		this.modelMapper=modelMapper;
		this.passwordEncoder=passwordEncoder;
	}

	@Override
	public UserResponseDto createUser(UserRequestDto userReqDto)
	{
		UserResponseDto userDto = modelMapper.map(userReqDto, UserResponseDto.class);
		User user = dtoToUser(userDto);
		Role role = roleRepo.findByName("ROLE_USER")
				.orElseThrow(()->new RuntimeException("Role not found"));	
		if(userRepo.findByEmail(user.getEmail()).isPresent())
			throw new EmailAlreadyExistException("This Email already exist. Try different Email");
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setRole(Set.of(role));
		userRepo.save(user);

		return userToDto(user);
	}

	@Override
	public UserResponseDto updateUser(UserRequestDto userReqDto, Long id)
	{
		User user = userRepo.findById(id)
				.orElseThrow(()->new UserNotFoundException("No user found"));
		user.setName(userReqDto.getName());
		user.setEmail(userReqDto.getEmail());
		user.setPassword(passwordEncoder.encode(userReqDto.getPassword()));
		user.setAbout(userReqDto.getAbout());
		User savedUser= userRepo.save(user);

		return userToDto(savedUser);
	}

	@Override
	public UserResponseDto getUserById(Long id)
	{
		User user = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not found"));

		return userToDto(user);
	}
	
	@Override
	public UserProfile getUserProfile(Long id)
	{
		User user = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not found"));

		return modelMapper.map(user, UserProfile.class);
	}


	@Override
	public UserResponseDto getUserByEmail(String email)
	{
		User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not found"));

		return userToDto(user);
	}

	@Override
	public List<UserResponseDto> getAllUser()
	{
		List<User> users = userRepo.findAll();
//		List<UserDto> usersDto= users.stream().map(this::userToDto).toList();
		List<UserResponseDto> userDtos= users.stream()
				.map(user->userToDto(user))
				.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Long id)
	{
		User user = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not found"));
		userRepo.delete(user);

	}

	public User dtoToUser(UserResponseDto userDto)
	{
		User user = modelMapper.map(userDto, User.class);
		return user;
	}

	public UserResponseDto userToDto(User user)
	{
		UserResponseDto userDto = modelMapper.map(user, UserResponseDto.class);
		
		if(user.getRole() !=null) {
			Set<String> roles = user.getRole()
						.stream()
						.map(Role::getName)
						.collect(Collectors.toSet());
			userDto.setRoles(roles);
		}
		
		return userDto;
	}

	
}
