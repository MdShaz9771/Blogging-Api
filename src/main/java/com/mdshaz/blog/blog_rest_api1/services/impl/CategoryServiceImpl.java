package com.mdshaz.blog.blog_rest_api1.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mdshaz.blog.blog_rest_api1.entity.Category;
import com.mdshaz.blog.blog_rest_api1.exceptions.AlreadyExistException;
import com.mdshaz.blog.blog_rest_api1.exceptions.ResourceNotFoundException;
import com.mdshaz.blog.blog_rest_api1.payloads.CategoryRequestDto;
import com.mdshaz.blog.blog_rest_api1.payloads.CategoryResponseDto;
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
	public CategoryResponseDto addCategory(CategoryRequestDto categoryReqDto)
	{
		Category category = modelMapper.map(categoryReqDto, Category.class);
		if(categoryRepo.findByName(categoryReqDto.getName()).isPresent())
			throw new AlreadyExistException("Category Already exist");
	    Category savedCategory = categoryRepo.save(category);  
		
		return modelMapper.map(savedCategory, CategoryResponseDto.class);
	}

	@Override
	public CategoryResponseDto updateCategory(CategoryRequestDto categoryReqDto, Long categoryId)
	{
		Category category = modelMapper.map(categoryReqDto, Category.class);
		category.setId(categoryId);
		Category savedCategory= categoryRepo.save(category);
		return modelMapper.map(savedCategory, CategoryResponseDto.class);
	}

	@Override
	public CategoryResponseDto getCategoryByName(String categoryName)
	{
		if(categoryRepo.findByName(categoryName).isEmpty()) {
			throw new ResourceNotFoundException("Category not found with name: "+categoryName);
		}
		Category category = categoryRepo.findByName(categoryName).get();
		return modelMapper.map(category, CategoryResponseDto.class);
	}

	@Override
	public CategoryResponseDto getCategoryById(Long categoryId)
	{
		if(categoryRepo.findById(categoryId).isEmpty()) {
			throw new ResourceNotFoundException("Category not found with Id: "+categoryId);
		}
		Category category = categoryRepo.findById(categoryId).get();
		return modelMapper.map(category, CategoryResponseDto.class);
	}

	@Override
	public List<CategoryResponseDto> getAllCategory()
	{
		if(categoryRepo.findAll().isEmpty()) {
			throw new ResourceNotFoundException("No category found");
		}
		List<Category> categories = categoryRepo.findAll();
		
		List<CategoryResponseDto> categoryDto= categories.stream().map(e->{
			CategoryResponseDto categoryResp = modelMapper.map(e, CategoryResponseDto.class);
			return categoryResp;
		}).toList();
		
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
	
}
