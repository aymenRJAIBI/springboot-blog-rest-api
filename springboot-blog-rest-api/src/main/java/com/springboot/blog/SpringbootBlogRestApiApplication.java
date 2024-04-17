package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info (
				title ="Spring Boot Blog App REST APIs",
				description = "Spring Boot Blog REST APIs Documentation",
				version ="v1.0",
				contact = @Contact(
							name="Aymen",
							email ="aymenrjaibi2301@gmail.com"
				),
					license = @License(
							name = "Apache License 2.0"
					)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog App Documentation",
				url = "https://github.com/aymenRJAIBI/springboot-blog-rest-api"
		)
)

public class SpringbootBlogRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

}
