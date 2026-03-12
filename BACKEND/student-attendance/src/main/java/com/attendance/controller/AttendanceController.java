package com.attendance.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.attendance.dto.AttendanceCreateRequest;
import com.attendance.entity.Attendance;
import com.attendance.entity.Course;
import com.attendance.entity.Location;
import com.attendance.entity.StudentProfile;
import com.attendance.repository.AttendanceRepository;
import com.attendance.repository.CourseRepository;
import com.attendance.repository.LocationRepository;
import com.attendance.repository.StudentProfileRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

  private final AttendanceRepository attendanceRepo;
  private final StudentProfileRepository studentRepo;
  private final CourseRepository courseRepo;
  private final LocationRepository locationRepo;

  @PostMapping
  public Attendance create(@Valid @RequestBody AttendanceCreateRequest req) {
    StudentProfile student = studentRepo.findById(req.studentId())
        .orElseThrow(() -> new IllegalArgumentException("Student not found: " + req.studentId()));

    Course course = courseRepo.findById(req.courseId())
        .orElseThrow(() -> new IllegalArgumentException("Course not found: " + req.courseId()));

    Location location = locationRepo.findById(req.locationId())
        .orElseThrow(() -> new IllegalArgumentException("Location not found: " + req.locationId()));

    Attendance attendance = new Attendance();
    attendance.setStudent(student);
    attendance.setCourse(course);
    attendance.setLocation(location);
    attendance.setAttendedOn(req.attendedOn());
    attendance.setStatus(req.status());
    return attendanceRepo.save(attendance);
  }

  @GetMapping
  public Page<Attendance> list(@PageableDefault(size = 10, sort = "attendedOn") Pageable pageable) {
    return attendanceRepo.findAll(pageable);
  }

  @GetMapping("/{id}")
  public Attendance getById(@PathVariable Long id) {
    return attendanceRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance not found: " + id));
  }

  @PutMapping("/{id}")
  public Attendance update(@PathVariable Long id, @Valid @RequestBody AttendanceCreateRequest req) {
    Attendance attendance = attendanceRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Attendance not found: " + id));

    StudentProfile student = studentRepo.findById(req.studentId())
        .orElseThrow(() -> new IllegalArgumentException("Student not found: " + req.studentId()));

    Course course = courseRepo.findById(req.courseId())
        .orElseThrow(() -> new IllegalArgumentException("Course not found: " + req.courseId()));

    Location location = locationRepo.findById(req.locationId())
        .orElseThrow(() -> new IllegalArgumentException("Location not found: " + req.locationId()));

    attendance.setStudent(student);
    attendance.setCourse(course);
    attendance.setLocation(location);
    attendance.setAttendedOn(req.attendedOn());
    attendance.setStatus(req.status());
    return attendanceRepo.save(attendance);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    attendanceRepo.deleteById(id);
  }
}
