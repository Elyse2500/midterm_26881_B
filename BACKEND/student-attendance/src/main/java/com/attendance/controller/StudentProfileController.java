package com.attendance.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.attendance.dto.StudentProfileCreateRequest;
import com.attendance.entity.AppUser;
import com.attendance.entity.StudentProfile;
import com.attendance.repository.AppUserRepository;
import com.attendance.repository.StudentProfileRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentProfileController {

  private final StudentProfileRepository studentRepo;
  private final AppUserRepository userRepo;

  @PostMapping
  public StudentProfile create(@Valid @RequestBody StudentProfileCreateRequest req) {
    AppUser user = userRepo.findById(req.userId())
        .orElseThrow(() -> new IllegalArgumentException("User not found: " + req.userId()));

    StudentProfile student = new StudentProfile();
    student.setStudentNumber(req.studentNumber());
    student.setUser(user);
    return studentRepo.save(student);
  }

  @GetMapping
  public Page<StudentProfile> list(@PageableDefault(size = 10, sort = "studentNumber") Pageable pageable) {
    return studentRepo.findAll(pageable);
  }

  @GetMapping("/{id}")
  public StudentProfile getById(@PathVariable Long id) {
    return studentRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found: " + id));
  }

  @PutMapping("/{id}")
  public StudentProfile update(@PathVariable Long id, @Valid @RequestBody StudentProfileCreateRequest req) {
    StudentProfile student = studentRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Student not found: " + id));

    AppUser user = userRepo.findById(req.userId())
        .orElseThrow(() -> new IllegalArgumentException("User not found: " + req.userId()));

    student.setStudentNumber(req.studentNumber());
    student.setUser(user);
    return studentRepo.save(student);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    studentRepo.deleteById(id);
  }
}
