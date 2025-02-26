package com.practice.config;

import org.springdoc.core.models.GroupedOpenApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig 
{
	@Bean
	public GroupedOpenApi demoPlansApi()
	{
		return GroupedOpenApi.builder()
				.group("demo-plans") // Group name for better organization in Swagger UI
				.packagesToScan("com.practice.controller") // Base package to include
				.build();
	}
}
