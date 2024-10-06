package com.mdshaz.blog.blog_rest_api1.payloads;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Data transfer object representing the JWT token response")
public class JwtResponseDto {

    @Schema(description = "JWT token for authenticated access", example = "eyJhbGciOiJIUzI1...")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtResponseDto(String token) {
        this.token = token;
    }
}
