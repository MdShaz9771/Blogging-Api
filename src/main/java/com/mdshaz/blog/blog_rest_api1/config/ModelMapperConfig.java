package com.mdshaz.blog.blog_rest_api1.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig
{
    @Bean
     ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
