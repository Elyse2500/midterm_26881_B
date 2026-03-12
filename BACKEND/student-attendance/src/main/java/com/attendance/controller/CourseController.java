package com.attendance.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.attendance.dto.CourseCreateRequest;
import com.attendance.entity.Course;
import com.attendance.repository.CourseRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

  private final CourseRepository courseRepo;

  @PostMapping
  public Course create(@Valid @RequestBody CourseCreateRequest req) {
    Course course = new Course();
    course.setCode(req.code());
    course.setTitle(req.title());
    return courseRepo.save(course);
  }

  @GetMapping
  public Page<Course> list(@PageableDefault(size = 10, sort = "title") Pageable pageable) {
    return courseRepo.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Course getById(@PathVariable Long id) {
    return courseRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Course not found: " + id));
  }

  @PutMapping("/{id}")
  public Course update(@PathVariable Long id, @Valid @RequestBody CourseCreateRequest req) {
    Course course = courseRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Course not found: " + id));
    
    course.setCode(req.code());
    course.setTitle(req.title());
    return courseRepo.save(course);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    courseRepo.deleteById(id);
  }
}
