package com.mdshaz.blog.blog_rest_api1.exceptions;

public class ResourceNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	

}
