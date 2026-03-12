package com.attendance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateRequest(
  @NotBlank String fullName,
  @NotBlank @Email String email,
  @NotBlank String provinceCode
) {}
