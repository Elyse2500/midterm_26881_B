package com.attendance.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
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
@Table(name = "attendance",
  uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id", "attended_on"}))
@Getter @Setter
public class Attendance {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "student_id")
  private StudentProfile student;

  @ManyToOne(optional = false)
  @JoinColumn(name = "course_id")
  private Course course;

  @ManyToOne(optional = false)
  @JoinColumn(name = "location_id")
  private Location location;

  @Column(name = "attended_on", nullable = false)
  private LocalDate attendedOn;

  @Column(nullable = false)
  private String status; // PRESENT, ABSENT, LATE
}
