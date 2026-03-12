package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.entity.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

}