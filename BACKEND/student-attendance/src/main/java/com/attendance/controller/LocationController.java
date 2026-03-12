package com.attendance.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.attendance.dto.LocationCreateRequest;
import com.attendance.entity.Location;
import com.attendance.repository.LocationRepository;
import com.attendance.service.LocationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

  private final LocationService locationService;
  private final LocationRepository locationRepo;

  // CREATE
  @PostMapping
  public Location create(@Valid @RequestBody LocationCreateRequest req) {
    return locationService.create(req);
  }

  // READ (list with pagination + sorting)
  @GetMapping
  public Page<Location> list(
      @PageableDefault(size = 10, sort = "campusName") Pageable pageable) {
    return locationRepo.findAll(pageable);
  }

  // READ by ID
  @GetMapping("/{id}")
  public Location getById(@PathVariable Long id) {
    return locationRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Location not found: " + id));
  }

  // UPDATE
  @PutMapping("/{id}")
  public Location update(@PathVariable Long id,
                         @Valid @RequestBody LocationCreateRequest req) {

    Location location = locationRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Location not found: " + id));

    location.setCampusName(req.campusName());
    location.setRoom(req.room());

    return locationRepo.save(location);
  }

  // DELETE
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    locationRepo.deleteById(id);
  }
}