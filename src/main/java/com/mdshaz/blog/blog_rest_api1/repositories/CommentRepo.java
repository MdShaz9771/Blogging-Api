package com.mdshaz.blog.blog_rest_api1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdshaz.blog.blog_rest_api1.entity.Comment;
import com.mdshaz.blog.blog_rest_api1.entity.Post;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>
{
	Long countByPost(Post post);

	

}
