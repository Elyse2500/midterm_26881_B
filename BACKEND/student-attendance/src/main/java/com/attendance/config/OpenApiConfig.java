package com.attendance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI api() {
    return new OpenAPI().info(new Info()
      .title("Student Attendance API")
      .version("1.0.0")
      .description("Attendance system with ERD relationships, pagination, sorting, and Swagger docs"));
  }
}