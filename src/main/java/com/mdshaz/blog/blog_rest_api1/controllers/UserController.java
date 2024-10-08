package com.mdshaz.blog.blog_rest_api1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdshaz.blog.blog_rest_api1.payloads.UserProfile;
import com.mdshaz.blog.blog_rest_api1.payloads.UserRequestDto;
import com.mdshaz.blog.blog_rest_api1.payloads.UserResponseDto;
import com.mdshaz.blog.blog_rest_api1.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users Api", description = "Endpoints for user management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    @Operation(summary = "Create a user", description = "Adds a new user")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userReqDto) {
        UserResponseDto savedUser = userService.createUser(userReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    @Operation(summary = "Get all users(For Admins only)", description = "Retrieves a list of all users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUser();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Updates an existing user")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto userReqDto) {
        UserResponseDto user = userService.updateUser(userReqDto, id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user", description = "Retrieves a specific user by ID")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    
    @GetMapping("/{id}/profile")
    @Operation(summary = "Get a profile of user", description = "Retrieves a specific user profile by ID")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long id) {
    	UserProfile user = userService.getUserProfile(id);
    	return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user (For Admins only)", description = "Deletes a specific user by ID")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
