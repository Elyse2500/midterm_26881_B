package com.attendance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "locations")
@Getter @Setter
public class Location {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String campusName;

  @Column(nullable = false)
  private String room;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "province_code", referencedColumnName = "code")
  private Province province;
}
