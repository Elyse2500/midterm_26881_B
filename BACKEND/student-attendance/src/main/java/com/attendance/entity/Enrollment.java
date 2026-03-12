package com.attendance.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enrollments",
  uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
@Getter @Setter
public class Enrollment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "student_id")
  private StudentProfile student;

  @ManyToOne(optional = false)
  @JoinColumn(name = "course_id")
  private Course course;

  private LocalDate enrolledAt = LocalDate.now();
}
