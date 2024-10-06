package com.mdshaz.blog.blog_rest_api1.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Data transfer object for login request containing username and password")
public class LoginRequestDto {

	@Schema(description = "Username or email for login", example = "john_doe")
	@NotBlank(message = "Username should not be blank")
    private String username;

	@Schema(description = "Password for login", example = "mypassword123")
	@NotBlank(message = "Password should not be blank")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
