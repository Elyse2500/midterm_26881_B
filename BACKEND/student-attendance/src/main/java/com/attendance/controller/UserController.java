package com.attendance.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.attendance.dto.UserCreateRequest;
import com.attendance.entity.AppUser;
import com.attendance.repository.AppUserRepository;
import com.attendance.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final AppUserRepository userRepo;
  private final UserService userService;

  // CREATE
  @PostMapping
  public AppUser create(@Valid @RequestBody UserCreateRequest req) {
    return userService.create(req);
  }

  // READ (list with pagination + sorting)
  @GetMapping
  public Page<AppUser> list(
      @PageableDefault(size = 10, sort = "fullName") Pageable pageable) {
    return userRepo.findAll(pageable);
  }

  // READ by ID
  @GetMapping("/{id}")
  public AppUser getById(@PathVariable Long id) {
    return userRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found: " + id));
  }

  // READ by province (code or name)
  @GetMapping("/by-province")
  public Page<AppUser> getUsersByProvince(
      @RequestParam String value,
      @PageableDefault(size = 10, sort = "fullName") Pageable pageable
  ) {
    Page<AppUser> users = userRepo.findByProvince_CodeIgnoreCase(value, pageable);
    if (users.isEmpty()) {
      users = userRepo.findByProvince_NameIgnoreCase(value, pageable);
    }
    return users;
  }

  // UPDATE
  @PutMapping("/{id}")
  public AppUser update(@PathVariable Long id,
                        @Valid @RequestBody UserCreateRequest req) {
    return userService.update(id, req);
  }

  // DELETE
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    userRepo.deleteById(id);
  }
}