# 🌟 Blogging API with Spring Boot 🌟

This is a comprehensive **Blogging API** built using **Spring Boot**, equipped with advanced security, role-based authentication and authorization, pagination, sorting, and a host of CRUD operations. The project is well-documented with **Swagger** for easy exploration and testing.

## 🛠 Features

### 🔑 Authentication & Authorization
- **JWT-Based Role Management**: Secure the endpoints using **JWT** with support for both _User_ and _Admin_ roles.
- **User Registration & Login**: Register new users and authenticate using JWT tokens for secure access.

### 📝 User Operations
- **CRUD Operations on Posts**: Users can create, update, view, and delete posts, comment, and like posts.
- **Category-Based Search**: Posts are divided into categories, allowing users to search by category.
- **Pagination & Sorting**: Responses are pageable and sortable for efficient data handling.
- **Enhanced Search Feature**: Users can search posts based on keywords, making it easier to find relevant content.

### ⚙ Admin Operations
- **User Management**: Admins can delete users and manage their roles.
- **Role Management**: Admins can add or delete roles to maintain control over user access.

## 🚀 Technologies Used
- **Backend**: Spring Boot, Spring Security (JWT), Spring Data JPA
- **Database**: MySQL
- **Documentation**: Swagger

## 🗂 Project Structure

```plaintext
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---mdshaz
|   |   |           \---blog
|   |   |               \---blog_rest_api1
|   |   |                   |   BlogRestApi1Application.java
|   |   |                   |
|   |   |                   +---config
|   |   |                   |       AppConstants.java
|   |   |                   |       DemoController.java
|   |   |                   |       ModelMapperConfig.java
|   |   |                   |       SecurityConfig.java
|   |   |                   |       SwaggerConfig.java
|   |   |                   |
|   |   |                   +---controllers
|   |   |                   |       AuthController.java
|   |   |                   |       CategoryController.java
|   |   |                   |       CommentController.java
|   |   |                   |       LikeController.java
|   |   |                   |       PostController.java
|   |   |                   |       RoleController.java
|   |   |                   |       UserController.java
|   |   |                   |
|   |   |                   +---entity
|   |   |                   |       Category.java
|   |   |                   |       Comment.java
|   |   |                   |       Like.java
|   |   |                   |       Post.java
|   |   |                   |       Role.java
|   |   |                   |       User.java
|   |   |                   |
|   |   |                   +---exceptions
|   |   |                   |       AlreadyExistException.java
|   |   |                   |       EmailAlreadyExistException.java
|   |   |                   |       ErrorDetails.java
|   |   |                   |       GlobalExceptionHandler.java
|   |   |                   |       ResourceNotFoundException.java
|   |   |                   |       UserNotFoundException.java
|   |   |                   |
|   |   |                   +---payloads
|   |   |                   |       CategoryDto.java
|   |   |                   |       CommentDto.java
|   |   |                   |       JwtResponseDto.java
|   |   |                   |       LoginRequestDto.java
|   |   |                   |       PostDto.java
|   |   |                   |       PostResponse.java
|   |   |                   |       RoleDto.java
|   |   |                   |       UserDto.java
|   |   |                   |
|   |   |                   +---repositories
|   |   |                   |       CategoryRepo.java
|   |   |                   |       CommentRepo.java
|   |   |                   |       LikeRepo.java
|   |   |                   |       PostRepo.java
|   |   |                   |       RoleRepo.java
|   |   |                   |       UserRepo.java
|   |   |                   |
|   |   |                   +---security
|   |   |                   |       CustomUserDetails.java
|   |   |                   |       CustomUserDetailsService.java
|   |   |                   |       JwtFilter.java
|   |   |                   |       JwtService.java
|   |   |                   |       UserVerificationService.java
|   |   |                   |
|   |   |                   +---services
|   |   |                   |   |   CategoryService.java
|   |   |                   |   |   CommentService.java
|   |   |                   |   |   LikeService.java
|   |   |                   |   |   PostService.java
|   |   |                   |   |   RoleService.java
|   |   |                   |   |   UserService.java
|   |   |                   |   |
|   |   |                   |   \---impl
|   |   |                   |           CategoryServiceImpl.java
|   |   |                   |           CommentServiceImpl.java
|   |   |                   |           LikeServiceImpl.java
|   |   |                   |           PostServiceImpl.java
|   |   |                   |           RoleServiceImpl.java
|   |   |                   |           UserServiceImpl.java
|   |   |                   |
|   |   |                   \---util
|   |   \---resources
|   |       |   application-dev.properties
|   |       |   application-prod.properties
|   |       |   application.properties
|   |       |
|   |       \---META-INF
|   |               additional-spring-configuration-metadata.json
|   |
|   \---test
|       \---java
|           \---com
|               \---mdshaz
|                   \---blog
|                       \---blog_rest_api1
|                               BlogRestApi1ApplicationTests.java
