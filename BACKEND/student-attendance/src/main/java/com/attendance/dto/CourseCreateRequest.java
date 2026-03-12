package com.attendance.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseCreateRequest(
  @NotBlank String code,
  @NotBlank String title
) {}
