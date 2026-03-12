package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
