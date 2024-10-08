package com.mdshaz.blog.blog_rest_api1.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UserRequestDto
{
	@NotBlank(message = "Name should not be blank")
    @Size(min = 3, max = 30, message = "Name size must be between 3 to 30 characters")
    @Schema(description = "Name of the user", example = "John Doe")
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    @Schema(description = "Email address of the user", example = "john@example.com")
    private String email;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 4, max = 30, message = "Password size must be between 4 to 30 characters")
    @Schema(description = "Password for the user account", example = "mypassword123")
    private String password;

    @NotBlank(message = "About section should not be blank")
    @Size(min = 8, max = 120, message = "About section size must be between 8 to 120 characters")
    @Schema(description = "Short description about the user", example = "A passionate blogger and software developer.")
    private String about;

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
    
    

}
