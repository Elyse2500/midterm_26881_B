package com.attendance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentProfileCreateRequest(
  @NotBlank String studentNumber,
  @NotNull Long userId
) {}
