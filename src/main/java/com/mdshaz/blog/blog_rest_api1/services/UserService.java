package com.mdshaz.blog.blog_rest_api1.services;

import java.util.List;

import com.mdshaz.blog.blog_rest_api1.entity.User;
import com.mdshaz.blog.blog_rest_api1.payloads.UserDto;
import com.mdshaz.blog.blog_rest_api1.payloads.UserRequestDto;


public interface UserService
{
	UserDto createUser(UserRequestDto user);
	UserDto updateUser(UserRequestDto user, Long id);
	UserDto getUserById(Long id);
	UserDto getUserByEmail(String email);
	List<UserDto> getAllUser();
	void deleteUser(Long id);
	User dtoToUser(UserDto userDto);
	UserDto userToDto(User user);

}
