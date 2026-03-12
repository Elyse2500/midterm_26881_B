package com.attendance.service;

import org.springframework.stereotype.Service;

import com.attendance.dto.UserCreateRequest;
import com.attendance.entity.AppUser;
import com.attendance.entity.Province;
import com.attendance.repository.AppUserRepository;
import com.attendance.repository.ProvinceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final AppUserRepository userRepo;
  private final ProvinceRepository provinceRepo;

  public AppUser create(UserCreateRequest req) {
    if (userRepo.existsByEmail(req.email())) {
      throw new IllegalArgumentException("Email already exists: " + req.email());
    }

    Province province = provinceRepo.findById(req.provinceCode())
      .orElseThrow(() -> new IllegalArgumentException("Province not found: " + req.provinceCode()));

    AppUser user = new AppUser();
    user.setFullName(req.fullName());
    user.setEmail(req.email());
    user.setProvince(province);

    try {
      return userRepo.save(user);
    } catch (Exception e) {
      throw new RuntimeException("Failed to create user: " + e.getMessage(), e);
    }
  }

  public AppUser update(Long id, UserCreateRequest req) {
    AppUser user = userRepo.findById(id)
      .orElseThrow(() -> new RuntimeException("User not found: " + id));

    if (!user.getEmail().equals(req.email()) && userRepo.existsByEmail(req.email())) {
      throw new IllegalArgumentException("Email already exists: " + req.email());
    }

    Province province = provinceRepo.findById(req.provinceCode())
      .orElseThrow(() -> new IllegalArgumentException("Province not found: " + req.provinceCode()));

    user.setFullName(req.fullName());
    user.setEmail(req.email());
    user.setProvince(province);

    return userRepo.save(user);
  }
}
