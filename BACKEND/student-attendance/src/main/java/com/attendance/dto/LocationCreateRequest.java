package com.attendance.dto;

import jakarta.validation.constraints.NotBlank;

public record LocationCreateRequest(
  @NotBlank String campusName,
  @NotBlank String room,
  @NotBlank String provinceCode
) {}
