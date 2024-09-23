package com.mdshaz.blog.blog_rest_api1.exceptions;

public class UserNotFoundException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public UserNotFoundException(String message)
	{
		super(message);
		this.message = message;
		
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
