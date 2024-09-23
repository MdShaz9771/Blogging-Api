package com.mdshaz.blog.blog_rest_api1.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mdshaz.blog.blog_rest_api1.entity.Category;
import com.mdshaz.blog.blog_rest_api1.exceptions.AlreadyExistException;
import com.mdshaz.blog.blog_rest_api1.exceptions.ResourceNotFoundException;
import com.mdshaz.blog.blog_rest_api1.payloads.CategoryDto;
import com.mdshaz.blog.blog_rest_api1.repositories.CategoryRepo;
import com.mdshaz.blog.blog_rest_api1.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService
{
	private ModelMapper modelMapper;
	private CategoryRepo categoryRepo;
	

	public CategoryServiceImpl(ModelMapper modelMapper,CategoryRepo categoryRepo)
	{
		this.modelMapper = modelMapper;
		this.categoryRepo = categoryRepo;
	}

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto)
	{
		Category category = categoryDtoToCategory(categoryDto);
		if(categoryRepo.findByName(categoryDto.getName()).isPresent())
			throw new AlreadyExistException("Category Already exist");
	    Category savedCategory = categoryRepo.save(category);  
		
		return categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId)
	{
		Category category = categoryDtoToCategory(categoryDto);
		category.setId(categoryId);
		Category savedCategory= categoryRepo.save(category);
		return categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto getCategoryByName(String categoryName)
	{
		if(categoryRepo.findByName(categoryName).isEmpty()) {
			throw new ResourceNotFoundException("Category not found with name: "+categoryName);
		}
		Category category = categoryRepo.findByName(categoryName).get();
		return categoryToCategoryDto(category);
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId)
	{
		if(categoryRepo.findById(categoryId).isEmpty()) {
			throw new ResourceNotFoundException("Category not found with Id: "+categoryId);
		}
		Category category = categoryRepo.findById(categoryId).get();
		return categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory()
	{
		if(categoryRepo.findAll().isEmpty()) {
			throw new ResourceNotFoundException("No category found");
		}
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> categoryDto= categories.stream().map(e->categoryToCategoryDto(e)).toList();
		
		return categoryDto;
	}

	@Override
	public void deleteCategory(Long categoryId)
	{
		if(categoryRepo.findById(categoryId).isEmpty()) {
			throw new ResourceNotFoundException("Category not found with Id: "+categoryId);
		}
		categoryRepo.deleteById(categoryId);
		
	}
	
	// Model Mapping
	public CategoryDto categoryToCategoryDto(Category category) {
		CategoryDto categoryDto=modelMapper.map(category, CategoryDto.class);
		return categoryDto;
		
	}
	public Category categoryDtoToCategory(CategoryDto categoryDto) {
		Category category=modelMapper.map(categoryDto, Category.class);
		return category;
		
	}
}
