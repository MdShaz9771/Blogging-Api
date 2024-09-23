package com.mdshaz.blog.blog_rest_api1.services;

import java.util.List;

import com.mdshaz.blog.blog_rest_api1.payloads.CategoryDto;


public interface CategoryService
{
	CategoryDto addCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
	CategoryDto getCategoryByName(String categoryName);
	CategoryDto getCategoryById(Long categoryId);
	List<CategoryDto> getAllCategory();
	void deleteCategory(Long categoryId);
	

}
