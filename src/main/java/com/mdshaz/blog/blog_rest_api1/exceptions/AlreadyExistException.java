package com.mdshaz.blog.blog_rest_api1.exceptions;

public class AlreadyExistException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public AlreadyExistException(String message) {
		super(message);
	}

}
