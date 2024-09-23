package com.mdshaz.blog.blog_rest_api1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdshaz.blog.blog_rest_api1.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>
{
	Optional<User> findByEmail(String email);

}
