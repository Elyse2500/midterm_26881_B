package com.attendance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  boolean existsByEmail(String email);

  Page<AppUser> findByProvince_CodeIgnoreCase(String code, Pageable pageable);
  Page<AppUser> findByProvince_NameIgnoreCase(String name, Pageable pageable);
}
