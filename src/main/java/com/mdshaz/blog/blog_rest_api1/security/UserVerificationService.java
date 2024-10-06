package com.mdshaz.blog.blog_rest_api1.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.mdshaz.blog.blog_rest_api1.payloads.LoginRequestDto;

@Service
public class UserVerificationService
{
	private AuthenticationManager authManager;
	private JwtService jwtService;

	public UserVerificationService(AuthenticationManager authManager,JwtService jwtService)
	{
		this.authManager = authManager;
		this.jwtService = jwtService;
	}
	
	public String verify(LoginRequestDto loginDto) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		if(authentication.isAuthenticated()) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			return jwtService.generateToken(userDetails);
		}else
			throw new BadCredentialsException("Enter valid Credentials");
		
	}
	
	

}
