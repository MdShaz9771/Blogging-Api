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

import com.mdshaz.blog.blog_rest_api1.payloads.CategoryDto;
import com.mdshaz.blog.blog_rest_api1.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
	private CategoryService categoryService;
	public CategoryController(CategoryService categoryService) {
		this.categoryService=categoryService;
	}
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto category= categoryService.addCategory(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Long id){
		CategoryDto category= categoryService.updateCategory(categoryDto, id);
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categories= categoryService.getAllCategory();
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
		CategoryDto category= categoryService.getCategoryById(id);
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}
//	@GetMapping("/{name}")
//	public ResponseEntity<CategoryDto> getCategory(@PathVariable String name){
//		CategoryDto category= categoryService.getCategoryByName(name);
//		return ResponseEntity.status(HttpStatus.OK).body(category);
//	}
	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long id){
		categoryService.deleteCategory(id);
		
		return ResponseEntity.noContent().build();
	}
	

}
