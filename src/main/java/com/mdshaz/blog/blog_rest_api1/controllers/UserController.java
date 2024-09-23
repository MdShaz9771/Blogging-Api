package com.mdshaz.blog.blog_rest_api1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdshaz.blog.blog_rest_api1.payloads.UserDto;
import com.mdshaz.blog.blog_rest_api1.services.UserService;

import jakarta.validation.Valid;

/*
 * Create User: POST /api/users
Get All Users: GET /api/users
Update User: PUT /api/users/{id}
Delete User: DELETE /api/users/{id}
This structure adheres to RESTful principles 
 */

@RestController
@RequestMapping("api/users")
public class UserController
{
	private UserService userService;

	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	@PostMapping("/")
	ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user)
	{
		UserDto savedUser = userService.createUser(user);
//		savedUser.setName(user.getName()); This is also a method
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

	}
	@GetMapping("/")
	ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users= userService.getAllUser();
		return ResponseEntity.ok().body(users);
	}
/* This is also a method to return with uri
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
    // Create and save the new User entity
    User savedUser = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getAbout());
    UserDto savedUserDto = userService.saveUser(savedUser);

    // Build the location URI (e.g., /users/{id})
    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUserDto.getId()) // Assuming your DTO has an ID field
            .toUri();

    // Return the response with the Location header and status 201 Created
    return ResponseEntity.created(location).body(savedUserDto);
}

*/
	// update
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto userDto){
		UserDto user= userService.updateUser(userDto, id);
		
		return ResponseEntity.ok(user);
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id){
		UserDto user= userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	// delete
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
		
	}

}
