package com.mdshaz.blog.blog_rest_api1.payloads;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserProfile
{
	@Schema(description = "Name of the user", example = "John Doe")
    private String name;

    @Schema(description = "Email address of the user", example = "john@example.com")
    private String email;

    @Schema(description = "Short description about the user", example = "A passionate blogger and software developer.")
    private String about;



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


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


}
