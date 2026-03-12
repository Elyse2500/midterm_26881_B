package com.attendance.service;

import org.springframework.stereotype.Service;

import com.attendance.dto.AttendanceCreateRequest;
import com.attendance.entity.Attendance;
import com.attendance.repository.AttendanceRepository;
import com.attendance.repository.CourseRepository;
import com.attendance.repository.LocationRepository;
import com.attendance.repository.StudentProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceService {
  private final AttendanceRepository attendanceRepo;
  private final StudentProfileRepository studentRepo;
  private final CourseRepository courseRepo;
  private final LocationRepository locationRepo;

  public Attendance mark(AttendanceCreateRequest req) {

    boolean already = attendanceRepo.existsByStudent_IdAndCourse_IdAndAttendedOn(
      req.studentId(), req.courseId(), req.attendedOn()
    );
    if (already) throw new IllegalStateException("Attendance already recorded.");

    Attendance a = new Attendance();
    a.setStudent(studentRepo.findById(req.studentId()).orElseThrow());
    a.setCourse(courseRepo.findById(req.courseId()).orElseThrow());
    a.setLocation(locationRepo.findById(req.locationId()).orElseThrow());
    a.setAttendedOn(req.attendedOn());
    a.setStatus(req.status());

    return attendanceRepo.save(a);
  }
}
