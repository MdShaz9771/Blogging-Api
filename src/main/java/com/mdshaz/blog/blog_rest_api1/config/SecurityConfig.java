package com.mdshaz.blog.blog_rest_api1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mdshaz.blog.blog_rest_api1.security.CustomUserDetailsService;
import com.mdshaz.blog.blog_rest_api1.security.JwtFilter;


@Configuration
@EnableMethodSecurity
public class SecurityConfig
{
	private CustomUserDetailsService userDetailsService;
	private JwtFilter jwtFilter;
	
	public SecurityConfig(CustomUserDetailsService userDetailsService, JwtFilter jwtFilter)
	{
		this.userDetailsService = userDetailsService;
		this.jwtFilter = jwtFilter;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf->csrf.disable())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(request->request
						.requestMatchers("/").permitAll()
						.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api-docs/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll() 
						.requestMatchers("/api/admin/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST,"/api/categories/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.PUT,"/api/categories/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE,"/api/categories/**").hasRole("ADMIN")
						.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		
	}

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(12);
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		return provider;
		
	}
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}

}
