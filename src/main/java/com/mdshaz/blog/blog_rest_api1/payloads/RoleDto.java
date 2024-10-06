package com.mdshaz.blog.blog_rest_api1.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Data transfer object representing a user role")
public class RoleDto {

    @Schema(description = "Unique ID of the role", example = "1")
    private long id;

    @NotBlank(message = "Role name should not be blank")
    @Size(min = 4, message = "Role name should be at least 4 characters")
    @Schema(description = "Name of the role", example = "ADMIN")
    private String name;

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
}
