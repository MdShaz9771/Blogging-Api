package com.mdshaz.blog.blog_rest_api1.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter
{
	private JwtService jwtService;
	private UserDetailsService userDetailsService;
	public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService)
	{
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        UserDetails userDetails =null;
        
        if(authHeader !=null && authHeader.startsWith("Bearer ")) {
        	token = authHeader.substring(7);
        	username=jwtService.extractUserName(token);
        }
        if(username !=null && SecurityContextHolder.getContext().getAuthentication() ==null) {
        	userDetails = userDetailsService.loadUserByUsername(username);
        	if(jwtService.validateToken(token, userDetails)) {
        		UsernamePasswordAuthenticationToken authentication = 
        				new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
        		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        		SecurityContextHolder.getContext().setAuthentication(authentication);
        		
        	}
        }
		
        filterChain.doFilter(request, response);
		
	}
	
	

}
