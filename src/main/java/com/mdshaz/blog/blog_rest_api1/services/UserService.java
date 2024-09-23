package com.mdshaz.blog.blog_rest_api1.services;

import java.util.List;

import com.mdshaz.blog.blog_rest_api1.entity.User;
import com.mdshaz.blog.blog_rest_api1.payloads.UserDto;


public interface UserService
{
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Long id);
	UserDto getUserById(Long id);
	UserDto getUserByEmail(String email);
	List<UserDto> getAllUser();
	void deleteUser(Long id);
	User dtoToUser(UserDto userDto);
	UserDto userToDto(User user);

}
