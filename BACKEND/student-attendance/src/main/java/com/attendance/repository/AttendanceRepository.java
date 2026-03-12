package com.attendance.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
  boolean existsByStudent_IdAndCourse_IdAndAttendedOn(Long studentId, Long courseId, LocalDate attendedOn);
}
