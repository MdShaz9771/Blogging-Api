package com.mdshaz.blog.blog_rest_api1.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdshaz.blog.blog_rest_api1.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>
{
	Optional<Role> findByName(String name);

}
