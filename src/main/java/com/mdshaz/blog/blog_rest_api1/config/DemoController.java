package com.mdshaz.blog.blog_rest_api1.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController
{
	@GetMapping("/")
	String sayWelcome() {
		return "Welcome to Shaz's Blogging Application";
	}

}
