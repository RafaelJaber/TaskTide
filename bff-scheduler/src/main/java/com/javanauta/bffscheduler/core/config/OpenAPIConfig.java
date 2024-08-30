package com.javanauta.bffscheduler.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    public static final String SECURITY_SCHEME = "bearerAuth";

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("BFF")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Tile API")
                        .version("1.0.0")
                        .description("This is a study project named Task Tile. The project is designed to practice and apply knowledge in API development using Spring Boot and OpenAPI. It utilizes various technologies including BFF (Backend for Frontend), MongoDB for NoSQL data storage, PostgreSQL for relational data storage, CRON for scheduling tasks, and email sending functionalities.")
                        .license(new License().name("Study License").url("https://opensource.org/licenses/MIT"))
                        .contact(new Contact()
                                .name("Rafael Jáber")
                                .url("https://www.linkedin.com/in/rafaeljaber/")
                        )
                )
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .name(SECURITY_SCHEME))
                );
    }
}
