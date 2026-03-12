package com.attendance.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.attendance.dto.EnrollmentCreateRequest;
import com.attendance.entity.Course;
import com.attendance.entity.Enrollment;
import com.attendance.entity.StudentProfile;
import com.attendance.repository.CourseRepository;
import com.attendance.repository.EnrollmentRepository;
import com.attendance.repository.StudentProfileRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

  private final EnrollmentRepository enrollmentRepo;
  private final StudentProfileRepository studentRepo;
  private final CourseRepository courseRepo;

  @PostMapping
  public Enrollment create(@Valid @RequestBody EnrollmentCreateRequest req) {
    StudentProfile student = studentRepo.findById(req.studentId())
        .orElseThrow(() -> new IllegalArgumentException("Student not found: " + req.studentId()));

    Course course = courseRepo.findById(req.courseId())
        .orElseThrow(() -> new IllegalArgumentException("Course not found: " + req.courseId()));

    Enrollment enrollment = new Enrollment();
    enrollment.setStudent(student);
    enrollment.setCourse(course);
    return enrollmentRepo.save(enrollment);
  }

  @GetMapping
  public Page<Enrollment> list(@PageableDefault(size = 10) Pageable pageable) {
    return enrollmentRepo.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Enrollment getById(@PathVariable Long id) {
    return enrollmentRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Enrollment not found: " + id));
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    enrollmentRepo.deleteById(id);
  }
}
