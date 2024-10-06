package com.mdshaz.blog.blog_rest_api1.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfig
{
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Blogging App API")
                .version("1.0.0")
                .description("This is the API documentation for the Blogging App. It covers all the API endpoints for managing users, posts, categories, and more.")
                .contact(new Contact()
                    .name("Md Shaz")
                    .email("mdshaz9931@gmail.com")
                    .url("https://www.linkedin.com/in/md-shaz-a772a9214")
                )
            )
            .servers(Arrays.asList(
            		new Server().url("https://shazblogapp-57a42fb824ff.herokuapp.com").description("Production Server"),
                    new Server().url("http://localhost:8080").description("Local Development Server")
                    )
            )
            .tags(Arrays.asList(
                    new Tag().name("Authentication Api").description("Authentication related endpoints"),
                    new Tag().name("Users Api").description("User management operations"),
                    new Tag().name("Posts Api").description("Post management operations"),
                    new Tag().name("Categories Api").description("Category management operations"),
                    new Tag().name("Comments Api").description("Comment management operations"),
                    new Tag().name("Likes Api").description("Like management operations"),
                    new Tag().name("Roles Api").description("Role management operations")
            ))
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth")) // Apply JWT security globally
            .components(new Components()
                    .addSecuritySchemes("bearerAuth", new SecurityScheme()
                        .name("JWT Authorisation")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .description("JWT Bearer Token")
                    )
                );
    }
	
}
