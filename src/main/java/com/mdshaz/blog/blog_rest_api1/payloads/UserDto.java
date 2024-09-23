package com.mdshaz.blog.blog_rest_api1.payloads;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto
{
	private long id;
	
	@NotBlank(message = "Name should not blank")
	@Size(min = 3,max = 30, message = "Name size must be between 3 to 30 characters")
	private String name;
	
	@NotBlank(message = "Email must not be blank")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotBlank(message = "Password should not blank")
	@Size(min = 4,max = 30, message = "Password size must be between 3 to 30 characters")
	private String password;
	
	@NotBlank(message = "About section should not blank")
	@Size(min = 8,max = 120, message = "Password size must be between 8 to 120 characters")
	private String about;
	private Set<String> roles;
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getAbout()
	{
		return about;
	}
	public void setAbout(String about)
	{
		this.about = about;
	}
	
	public Set<String> getRoles()
	{
		return roles;
	}
	public void setRoles(Set<String> roles)
	{
		this.roles = roles;
	}
	public UserDto()
	{
	}
	
	

}
