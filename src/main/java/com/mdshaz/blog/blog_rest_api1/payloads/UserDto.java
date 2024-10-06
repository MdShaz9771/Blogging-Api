package com.mdshaz.blog.blog_rest_api1.payloads;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data transfer object representing a user")
public class UserDto {

    @Schema(description = "Unique ID of the user", example = "1")
    private long id;

    @Schema(description = "Name of the user", example = "John Doe")
    private String name;

    @Schema(description = "Email address of the user", example = "john@example.com")
    private String email;

    @Schema(description = "Password for the user account", example = "mypassword123")
    private String password;

    @Schema(description = "Short description about the user", example = "A passionate blogger and software developer.")
    private String about;

    @Schema(description = "Roles assigned to the user", example = "[\"USER\", \"ADMIN\"]")
    private Set<String> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public UserDto() {
    }
}
