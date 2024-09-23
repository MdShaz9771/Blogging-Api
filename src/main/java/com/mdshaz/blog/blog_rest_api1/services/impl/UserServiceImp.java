package com.mdshaz.blog.blog_rest_api1.services.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mdshaz.blog.blog_rest_api1.entity.Role;
import com.mdshaz.blog.blog_rest_api1.entity.User;
import com.mdshaz.blog.blog_rest_api1.exceptions.EmailAlreadyExistException;
import com.mdshaz.blog.blog_rest_api1.exceptions.UserNotFoundException;
import com.mdshaz.blog.blog_rest_api1.payloads.UserDto;
import com.mdshaz.blog.blog_rest_api1.repositories.RoleRepo;
import com.mdshaz.blog.blog_rest_api1.repositories.UserRepo;
import com.mdshaz.blog.blog_rest_api1.services.UserService;

@Service
public class UserServiceImp implements UserService
{
	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private ModelMapper modelMapper;

	public UserServiceImp(UserRepo userRepo,RoleRepo roleRepo,ModelMapper modelMapper)
	{
		this.userRepo = userRepo;
		this.roleRepo=roleRepo;
		this.modelMapper=modelMapper;
	}

	@Override
	public UserDto createUser(UserDto userDto)
	{
		User user = dtoToUser(userDto);
		Role role = roleRepo.findByName("ROLE_USER")
				.orElseThrow(()->new RuntimeException("Role not found"));	
		if(userRepo.findByEmail(user.getEmail()).isPresent())
			throw new EmailAlreadyExistException("This Email already exist. Try different Email");
		
		
		user.setRole(Set.of(role));
		userRepo.save(user);

		return userToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long id)
	{
		User user = dtoToUser(userDto);
		user.setId(id);
		User savedUser= userRepo.save(user);

		return userToDto(savedUser);
	}

	@Override
	public UserDto getUserById(Long id)
	{
		User user = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Not found"));

		return userToDto(user);
	}

	@Override
	public UserDto getUserByEmail(String email)
	{
		User user = userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not found"));

		return userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser()
	{
		List<User> users = userRepo.findAll();
//		List<UserDto> usersDto= users.stream().map(this::userToDto).toList();
		List<UserDto> userDtos= users.stream()
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

	public User dtoToUser(UserDto userDto)
	{
		User user = modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}

	public UserDto userToDto(User user)
	{
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
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
