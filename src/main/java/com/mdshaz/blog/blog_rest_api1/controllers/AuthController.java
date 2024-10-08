package com.mdshaz.blog.blog_rest_api1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdshaz.blog.blog_rest_api1.payloads.JwtResponseDto;
import com.mdshaz.blog.blog_rest_api1.payloads.LoginRequestDto;
import com.mdshaz.blog.blog_rest_api1.payloads.UserResponseDto;
import com.mdshaz.blog.blog_rest_api1.payloads.UserRequestDto;
import com.mdshaz.blog.blog_rest_api1.security.UserVerificationService;
import com.mdshaz.blog.blog_rest_api1.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication Api", description = "Endpoints for user authentication")
public class AuthController {
    private UserVerificationService userVerificationService;
    private UserService userService;

    public AuthController(UserVerificationService userVerificationService, UserService userService) {
        this.userVerificationService = userVerificationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Logs in a user and returns a JWT")
    ResponseEntity<JwtResponseDto> authenticate(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        String token = userVerificationService.verify(loginRequestDto);
        JwtResponseDto response = new JwtResponseDto(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Creates a new user account")
    ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto userReqDto) {
        UserResponseDto user = userService.createUser(userReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
