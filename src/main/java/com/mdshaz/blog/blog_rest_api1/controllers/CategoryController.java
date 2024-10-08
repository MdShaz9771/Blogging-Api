package com.mdshaz.blog.blog_rest_api1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdshaz.blog.blog_rest_api1.payloads.CategoryRequestDto;
import com.mdshaz.blog.blog_rest_api1.payloads.CategoryResponseDto;
import com.mdshaz.blog.blog_rest_api1.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories Api", description = "Endpoints for category management")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    @Operation(summary = "Create a category (Only for Admins)", description = "Adds a new category")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryDto) {
        CategoryResponseDto category = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category (Only for Admins)", description = "Updates an existing category")
    public ResponseEntity<CategoryResponseDto> updateCategory(@Valid @RequestBody CategoryRequestDto categoryDto, @PathVariable Long id) {
        CategoryResponseDto category = categoryService.updateCategory(categoryDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @GetMapping("/")
    @Operation(summary = "Get all categories", description = "Retrieves a list of all categories")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        List<CategoryResponseDto> categories = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a category", description = "Retrieves a specific category by ID")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable Long id) {
        CategoryResponseDto category = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category (Only for Admins)", description = "Deletes a specific category by ID")
    public ResponseEntity<CategoryResponseDto> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
