package com.attendance.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student_profiles")
@Getter @Setter
public class StudentProfile {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String studentNumber;

  @OneToOne(optional = false)
  @JoinColumn(name = "user_id", unique = true)
  @JsonIgnore
  private AppUser user;

  @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Attendance> attendances = new ArrayList<>();
}
