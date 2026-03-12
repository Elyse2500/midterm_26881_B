package com.attendance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "provinces")
@Getter @Setter
public class Province {
  @Id
  @Column(length = 10)
  private String code;    

  @Column(nullable = false, unique = true)
  private String name;   

  @OneToMany(mappedBy = "province")
  @JsonIgnore  
  private List<AppUser> users;
}
