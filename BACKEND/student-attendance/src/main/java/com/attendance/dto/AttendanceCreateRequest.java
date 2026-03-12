package com.attendance.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttendanceCreateRequest(
  @NotNull Long studentId,
  @NotNull Long courseId,
  @NotNull Long locationId,
  @NotNull LocalDate attendedOn,
  @NotBlank String status
) {}
