package com.mdshaz.blog.blog_rest_api1.payloads;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data transfer object representing a blog category")
public class CategoryResponseDto {

    @Schema(description = "Unique ID of the category", example = "1")
    private long id;

    @Schema(description = "Name of the category", example = "Technology")
    private String name;

    @Schema(description = "Short description of the category", example = "This category covers technology-related posts.")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryResponseDto() {
    }
}
