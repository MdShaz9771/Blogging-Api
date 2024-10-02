package com.mdshaz.blog.blog_rest_api1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdshaz.blog.blog_rest_api1.entity.Like;
import com.mdshaz.blog.blog_rest_api1.entity.Post;
import com.mdshaz.blog.blog_rest_api1.entity.User;

@Repository
public interface LikeRepo extends JpaRepository<Like, Long>
{
	Long countByPost(Post post);
	
    boolean existsByUserAndPost(User user, Post post);

    Optional<Like> findByUserAndPost(User user, Post post);


    List<Like> findAllByUser(User user);

    List<Like> findAllByPost(Post post);

    // Delete a like by user and post
    void deleteByUserAndPost(User user, Post post);

}
