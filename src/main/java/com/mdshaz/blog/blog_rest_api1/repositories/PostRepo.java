package com.mdshaz.blog.blog_rest_api1.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mdshaz.blog.blog_rest_api1.entity.Category;
import com.mdshaz.blog.blog_rest_api1.entity.Post;
import com.mdshaz.blog.blog_rest_api1.entity.User;

public interface PostRepo extends JpaRepository<Post, Long>
{
	Page<Post> findByUser(User user,Pageable page);
	Page<Post> findByCategory(Category category,Pageable page);
	List<Post> findByTitleContainingIgnoreCase(String title);

}
