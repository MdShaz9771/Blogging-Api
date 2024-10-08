package com.mdshaz.blog.blog_rest_api1.services;

import java.util.List;

import com.mdshaz.blog.blog_rest_api1.payloads.CategoryRequestDto;
import com.mdshaz.blog.blog_rest_api1.payloads.CategoryResponseDto;


public interface CategoryService
{
	CategoryResponseDto addCategory(CategoryRequestDto categoryReqDto);
	CategoryResponseDto updateCategory(CategoryRequestDto categoryReqDto,Long categoryId);
	CategoryResponseDto getCategoryByName(String categoryName);
	CategoryResponseDto getCategoryById(Long categoryId);
	List<CategoryResponseDto> getAllCategory();
	void deleteCategory(Long categoryId);
	

}
