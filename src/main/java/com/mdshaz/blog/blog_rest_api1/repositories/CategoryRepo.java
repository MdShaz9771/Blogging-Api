package com.mdshaz.blog.blog_rest_api1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdshaz.blog.blog_rest_api1.entity.Category;


public interface CategoryRepo extends JpaRepository<Category, Long>
{
	Optional<Category> findByName(String name);

}
