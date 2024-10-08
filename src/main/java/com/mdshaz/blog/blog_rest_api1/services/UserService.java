package com.mdshaz.blog.blog_rest_api1.services;

import java.util.List;

import com.mdshaz.blog.blog_rest_api1.entity.User;
import com.mdshaz.blog.blog_rest_api1.payloads.UserResponseDto;
import com.mdshaz.blog.blog_rest_api1.payloads.UserProfile;
import com.mdshaz.blog.blog_rest_api1.payloads.UserRequestDto;


public interface UserService
{
	UserResponseDto createUser(UserRequestDto user);
	UserResponseDto updateUser(UserRequestDto user, Long id);
	UserResponseDto getUserById(Long id);
	UserProfile getUserProfile(Long id);
	UserResponseDto getUserByEmail(String email);
	List<UserResponseDto> getAllUser();
	void deleteUser(Long id);
	User dtoToUser(UserResponseDto userDto);
	UserResponseDto userToDto(User user);

}
