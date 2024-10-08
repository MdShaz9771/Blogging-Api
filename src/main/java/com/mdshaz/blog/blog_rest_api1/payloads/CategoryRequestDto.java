package com.mdshaz.blog.blog_rest_api1.payloads;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequestDto
{
    @NotBlank(message = "Category name should not be Blank")
    @Size(min = 4, max = 30, message = "Category name must be between 4 to 30 characters")
    @Schema(description = "Name of the category", example = "Technology")
    private String name;

    @Size(min = 4, max = 120, message = "Description must be between 4 and 120 characters")
    @Schema(description = "Short description of the category", example = "This category covers technology-related posts.")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
