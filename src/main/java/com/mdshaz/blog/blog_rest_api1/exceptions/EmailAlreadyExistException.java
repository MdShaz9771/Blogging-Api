package com.mdshaz.blog.blog_rest_api1.exceptions;

public class EmailAlreadyExistException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyExistException(String message) {
		super(message);
	}

}
