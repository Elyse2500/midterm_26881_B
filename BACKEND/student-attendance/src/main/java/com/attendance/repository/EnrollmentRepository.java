package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
  boolean existsByStudent_IdAndCourse_Id(Long studentId, Long courseId);
}
