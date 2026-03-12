package com.attendance.service;

import org.springframework.stereotype.Service;

import com.attendance.dto.LocationCreateRequest;
import com.attendance.entity.Location;
import com.attendance.entity.Province;
import com.attendance.repository.LocationRepository;
import com.attendance.repository.ProvinceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {
  private final LocationRepository locationRepo;
  private final ProvinceRepository provinceRepo;

  public Location create(LocationCreateRequest req) {
    Province p = provinceRepo.findById(req.provinceCode())
      .orElseThrow(() -> new IllegalArgumentException("Province not found: " + req.provinceCode()));

    Location loc = new Location();
    loc.setCampusName(req.campusName());
    loc.setRoom(req.room());
    loc.setProvince(p);

    return locationRepo.save(loc);
  }
}
