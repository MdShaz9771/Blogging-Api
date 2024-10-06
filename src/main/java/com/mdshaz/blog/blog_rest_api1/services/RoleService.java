package com.mdshaz.blog.blog_rest_api1.services;

import java.util.List;

import com.mdshaz.blog.blog_rest_api1.payloads.RoleDto;



public interface RoleService
{
	RoleDto addNewRole(RoleDto roleDto);
	void removeRole(Long roleId);
	List<RoleDto> findAllRoles();

	

}
