package com.attendance.dto;

import jakarta.validation.constraints.NotNull;

public record EnrollmentCreateRequest(
  @NotNull Long studentId,
  @NotNull Long courseId
) {}
