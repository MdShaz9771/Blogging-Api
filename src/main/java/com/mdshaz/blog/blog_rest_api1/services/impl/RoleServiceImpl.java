package com.mdshaz.blog.blog_rest_api1.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mdshaz.blog.blog_rest_api1.entity.Role;
import com.mdshaz.blog.blog_rest_api1.exceptions.ResourceNotFoundException;
import com.mdshaz.blog.blog_rest_api1.payloads.RoleDto;
import com.mdshaz.blog.blog_rest_api1.repositories.RoleRepo;
import com.mdshaz.blog.blog_rest_api1.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{
	private RoleRepo roleRepo;
	private ModelMapper modelMapper;
	

	public RoleServiceImpl(RoleRepo roleRepo, ModelMapper modelMapper)
	{
		this.roleRepo = roleRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public RoleDto addNewRole(RoleDto roleDto)
	{
		Role role = modelMapper.map(roleDto, Role.class);
		Role savedRole = roleRepo.save(role);
		
		return modelMapper.map(savedRole, RoleDto.class);
	}

	@Override
	public void removeRole(Long roleId)
	{
		Role role = roleRepo.findById(roleId)
				.orElseThrow(()->new ResourceNotFoundException("No role found with Id: "+roleId));
		roleRepo.delete(role);
		
	}

	@Override
	public List<RoleDto> findAllRoles()
	{
		List<Role> roles = roleRepo.findAll();
		List<RoleDto> roleDtos = roles.stream().map(role->{
			RoleDto roleDto= modelMapper.map(role, RoleDto.class);
			return roleDto;
		}).collect(Collectors.toList());
		return roleDtos;
	}

}
