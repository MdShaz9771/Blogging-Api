package com.mdshaz.blog.blog_rest_api1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdshaz.blog.blog_rest_api1.payloads.RoleDto;
import com.mdshaz.blog.blog_rest_api1.services.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admin/roles")
@Tag(name = "Roles Api", description = "Endpoints for role management")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    @Operation(summary = "Add a role", description = "Creates a new role")
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto) {
        RoleDto savedRole = roleService.addNewRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

    @DeleteMapping("{roleId}")
    @Operation(summary = "Delete a role", description = "Deletes a specific role by ID")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId) {
        roleService.removeRole(roleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    @Operation(summary = "Get all roles", description = "Retrieves a list of all roles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roles = roleService.findAllRoles();
        return ResponseEntity.ok(roles);
    }
}
