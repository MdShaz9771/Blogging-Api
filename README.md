<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blogging API - Spring Boot</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <!-- <link rel="stylesheet" href="style.css"> -->
    <style>
        /* Reset some default browser styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* General body styles */
        body {
            font-family: 'Helvetica Neue', Arial, sans-serif;
            background-color: #f3f6f9;
            color: #212529;
            margin: 0;
            padding: 0;
            line-height: 1.6;
        }

        /* Main container */
        .container {
            max-width: 900px;
            margin: 40px auto;
            background: #ffffff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.1);
        }

        /* Headings */
        h1 {
            font-size: 2.5rem;
            color: #004085;
            margin-bottom: 20px;
            text-align: center;
            border-bottom: 3px solid #004085;
            padding-bottom: 10px;
        }

        h2,
        h3 {
            color: #333333;
            margin-top: 30px;
            font-size: 1.75rem;
        }

        h2 {
            border-bottom: 2px solid #dee2e6;
            padding-bottom: 8px;
        }

        h3 {
            font-size: 1.5rem;
        }

        /* ................................................  */



        /* h1, h2 {
    color: #333;
}
h1 {
    text-align: center;
    margin-bottom: 30px;
} */
        .container1 {
            max-width: 800px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .api-section {
            margin-bottom: 20px;
            border-bottom: 1px solid #ccc;
            padding-bottom: 15px;
        }

        .api-title {
            font-size: 1.5em;
            color: #4CAF50;
            margin-bottom: 10px;
        }

        .endpoint {
            background: #e7f3fe;
            border-left: 4px solid #2196F3;
            padding: 10px;
            margin: 5px 0;
            border-radius: 4px;
        }

        .method {
            font-weight: bold;
        }

        .description {
            margin-left: 5px;
        }

        code {
            background: #e7f3fe;
            /* border-left: 4px solid #2196F3; */
            padding: .1em;
            padding-left: .3em;
            padding-right: .3em;
            margin: 5px 0;
            border-radius: 4px;
            font-size: 1em;
        }
        .run li{
            font-size: larger;

        }

        
    </style>
</head>

<body>
    <div class="container">
        <h1>🌟 Blogging API with Spring Boot 🌟</h1>
        <p>
            This is a comprehensive <strong>Blogging API</strong> built using
            <strong>Spring Boot</strong>, equipped with advanced security, role-based
            authentication and authorization, pagination, sorting, and a host of CRUD operations.
            The project is well-documented with <strong>Swagger</strong> for easy exploration and testing.
        </p>

        <h2>🛠 Features</h2>

        <h3><i class="fas fa-key"></i> Authentication & Authorization</h3>
        <ul>
            <li><strong>JWT-Based Role Management:</strong> Secure the endpoints using <strong>JWT</strong> with support
                for both <em>User</em> and <em>Admin</em> roles.</li>
            <li><strong>User Registration & Login:</strong> Register new users and authenticate using JWT tokens for
                secure access.</li>
        </ul>

        <h3><i class="fas fa-edit"></i> User Operations</h3>
        <ul>
            <li><strong>CRUD Operations on Posts:</strong> Users can create, update, view, and delete posts, comment,
                and like posts.</li>
            <li><strong>Category-Based Search:</strong> Posts are divided into categories, allowing users to search by
                category.</li>
            <li><strong>Pagination & Sorting:</strong> Responses are pageable and sortable for efficient data handling.
            </li>
            <li><strong>Enhanced Search Feature:</strong> Users can search posts based on keywords, making it easier to
                find relevant content.</li>
        </ul>

        <h3>⚙ Admin Operations</h3>
        <ul>
            <li><strong>User Management:</strong> Admins can delete users and manage their roles.</li>
            <li><strong>Role Management:</strong> Admins can add or delete roles to maintain control over user access.
            </li>
        </ul>

        <div class="highlight">
            <strong>Advanced Features:</strong>
            <ul>
                <li>Custom Exception Handling with user-friendly error messages.</li>
                <li>Full API Documentation via Swagger for easy testing and integration.</li>
            </ul>
        </div>

        <h2><i class="fas fa-wrench"></i> Technologies Used</h2>
        <ul>
            <li><strong>Backend:</strong> Spring Boot, Spring Security (JWT), Spring Data JPA</li>
            <li><strong>Database:</strong> MySQL</li>
            <li><strong>Documentation:</strong> Swagger</li>
        </ul>

        <h2><i class="fas fa-folder"></i> Project Structure</h2>
        <pre>
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
    </pre>

        <h2 id="database_heading"><i class="fas fa-database"></i> Database</h2>
        <img id="database_img" src="GitAssests\ER diagram.png" alt="Database schema image">

        <h2>🎯 How to Run the Project</h2>
        <h3>Prerequisites</h3>
        <ul>
            <li><strong>Latest Java</strong> installed</li>
            <li><strong>Maven</strong> installed</li>
            <li><strong>MySQL</strong> running</li>
        </ul>

        <div class="run">
            <h3>Steps to Run:</h3>
        <ol>
            <li>Clone the repository:
                <pre><code>git clone https://github.com/MdShaz9771/Blogging-Api.git</code></pre>
            </li>
            <li>Set up MySQL and update <code>application.properties</code> with your credentials:
                <pre><code>spring.datasource.url=jdbc:mysql://localhost:3306/blogging_db</code></pre>
                <pre><code>spring.datasource.username=your-username</code></pre>
                <pre><code>spring.datasource.password=your-password</code></pre>
            </li>
            <li>Build the project using Maven:
                <pre><code>mvn clean install</code></pre>
            </li>
            <li>Run the project:
                <pre><code>mvn spring-boot:run</code></pre>
            </li>
            <li>Access the API documentation via Swagger at:
                <pre><a href="http://localhost:8080/swagger-ui/index.html">http://localhost:8080/swagger-ui/index.html</a></pre>
            </li>
        </ol>
        </div>

        <h2><i class="fas fa-edit"></i> Endpoints</h2>
        <div class="container1">
            <div class="api-section">
                <div class="api-title">Authentication Api</div>
                <div class="endpoint"><span class="method">POST</span> <code>/api/auth/register</code> <span
                        class="description">Register new user</span></div>
                <div class="endpoint"><span class="method">POST</span> <code>/api/auth/login</code> <span
                        class="description">Authenticate user</span></div>
            </div>

            <div class="api-section">
                <div class="api-title">Users Api</div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/users/{id}</code> <span
                        class="description">Get a user</span></div>
                <div class="endpoint"><span class="method">PUT</span> <code>/api/users/{id}</code> <span
                        class="description">Update a user</span></div>
                <div class="endpoint"><span class="method">DELETE</span> <code>/api/users/{id}</code> <span
                        class="description">Delete a user</span></div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/users/</code> <span
                        class="description">Get all users</span></div>
                <div class="endpoint"><span class="method">POST</span> <code>/api/users/</code> <span
                        class="description">Create a user</span></div>
            </div>

            <div class="api-section">
                <div class="api-title">Likes Api</div>
                <div class="endpoint"><span class="method">POST</span>
                    <code>/api/users/{userId}/posts/{postId}/like</code> <span class="description">Add a like</span>
                </div>
                <div class="endpoint"><span class="method">DELETE</span>
                    <code>/api/users/{userId}/posts/{postId}/like</code> <span class="description">Remove a like</span>
                </div>
            </div>

            <div class="api-section">
                <div class="api-title">Posts Api</div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/users/posts/{postId}</code> <span
                        class="description">Get a post</span></div>
                <div class="endpoint"><span class="method">PUT</span> <code>/api/users/posts/{postId}</code> <span
                        class="description">Update a post</span></div>
                <div class="endpoint"><span class="method">DELETE</span> <code>/api/users/posts/{postId}</code> <span
                        class="description">Delete a post</span></div>
                <div class="endpoint"><span class="method">POST</span>
                    <code>/api/users/{userId}/categories/{categoryId}/posts</code> <span class="description">Create a
                        post</span>
                </div>
                <div class="endpoint"><span class="method">POST</span> <code>/api/users/posts/{id}/image/upload</code>
                    <span class="description">Upload an image</span>
                </div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/users/{userId}/posts</code> <span
                        class="description">Get all posts by user</span></div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/users/search</code> <span
                        class="description">Search posts</span></div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/users/posts</code> <span
                        class="description">Get all posts</span></div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/users/posts/image/{imageName}</code>
                    <span class="description">Get post image</span>
                </div>
                <div class="endpoint"><span class="method">GET</span>
                    <code>/api/users/categories/{categoryId}/posts</code> <span class="description">Get all posts by
                        category</span>
                </div>
            </div>

            <div class="api-section">
                <div class="api-title">Comments Api</div>
                <div class="endpoint"><span class="method">POST</span>
                    <code>/api/users/{userId}/posts/{postId}/comments</code> <span class="description">Add a
                        comment</span>
                </div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/users/posts/{postId}/comments</code>
                    <span class="description">Get all comments of a post</span>
                </div>
                <div class="endpoint"><span class="method">GET</span>
                    <code>/api/users/posts/{postId}/comments/count</code> <span class="description">Get total comments
                        for a post</span>
                </div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/users/posts/comments/{commentId}</code>
                    <span class="description">Get a comment by ID</span>
                </div>
                <div class="endpoint"><span class="method">DELETE</span>
                    <code>/api/users/posts/comments/{commentId}</code> <span class="description">Remove a comment</span>
                </div>
            </div>

            <div class="api-section">
                <div class="api-title">Roles Api</div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/admin/roles/</code> <span
                        class="description">Get all roles</span></div>
                <div class="endpoint"><span class="method">POST</span> <code>/api/admin/roles/</code> <span
                        class="description">Add a role</span></div>
                <div class="endpoint"><span class="method">DELETE</span> <code>/api/admin/roles/{roleId}</code> <span
                        class="description">Delete a role</span></div>
            </div>

            <div class="api-section">
                <div class="api-title">Categories Api</div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/categories/{id}</code> <span
                        class="description">Get a category</span></div>
                <div class="endpoint"><span class="method">PUT</span> <code>/api/categories/{id}</code> <span
                        class="description">Update a category</span></div>
                <div class="endpoint"><span class="method">DELETE</span> <code>/api/categories/{id}</code> <span
                        class="description">Delete a category</span></div>
                <div class="endpoint"><span class="method">GET</span> <code>/api/categories/</code> <span
                        class="description">Get all categories</span></div>
                <div class="endpoint"><span class="method">POST</span> <code>/api/categories/</code> <span
                        class="description">Create a category</span></div>
            </div>

        </div>

        <h2>🚀 Future Improvements</h2>
        <ul>
            <li>Implement social login (Google, GitHub OAuth2)</li>
            <li>Send email notifications for new posts or comments</li>
            <li>Enhance search functionality to support keyword search across posts and comments</li>
        </ul>

        <h2>👨‍💻 Contributions</h2>
        <p>
            Feel free to fork this project, raise issues, or contribute via pull requests. I'd love to collaborate with
            you!
        </p>

        <h2>📞 Contact</h2>
        <p>If you have any suggestions, or feedback, reach out to me on <a
                href="www.linkedin.com/in/md-shaz-a772a9214">LinkedIn</a>.</p>
    </div>
</body>

</html>