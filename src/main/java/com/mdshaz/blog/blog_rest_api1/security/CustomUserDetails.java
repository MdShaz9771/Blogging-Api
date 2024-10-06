package com.mdshaz.blog.blog_rest_api1.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mdshaz.blog.blog_rest_api1.entity.User;

public class CustomUserDetails implements UserDetails
{

	
	 
	private static final long serialVersionUID = 1L;
	
	private User user;
	

	public CustomUserDetails(User user)
	{
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return user.getRole().stream()
				.map(role-> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword()
	{
		return user.getPassword();
	}

	@Override
	public String getUsername()
	{
		return user.getEmail();
	}

}
