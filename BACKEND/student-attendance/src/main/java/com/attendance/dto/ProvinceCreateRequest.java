package com.attendance.dto;

import jakarta.validation.constraints.NotBlank;

public record ProvinceCreateRequest(
  @NotBlank String code,
  @NotBlank String name
) {}
