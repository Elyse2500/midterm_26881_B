package com.attendance.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.attendance.dto.ProvinceCreateRequest;
import com.attendance.entity.Province;
import com.attendance.repository.ProvinceRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/provinces")
@RequiredArgsConstructor
public class ProvinceController {

  private final ProvinceRepository provinceRepo;

  // CREATE
  @PostMapping
  public Province create(@Valid @RequestBody ProvinceCreateRequest req) {
    Province province = new Province();
    province.setCode(req.code());
    province.setName(req.name());
    return provinceRepo.save(province);
  }

  // READ (list with pagination + sorting)
  @GetMapping
  public Page<Province> list(
      @PageableDefault(size = 10, sort = "name") Pageable pageable) {
    return provinceRepo.findAll(pageable);
  }

  // READ by code
  @GetMapping("/{code}")
  public Province getByCode(@PathVariable String code) {
    return provinceRepo.findById(code)
        .orElseThrow(() -> new RuntimeException("Province not found: " + code));
  }

  // UPDATE
  @PutMapping("/{code}")
  public Province update(@PathVariable String code,
                         @Valid @RequestBody ProvinceCreateRequest req) {
    Province province = provinceRepo.findById(code)
        .orElseThrow(() -> new RuntimeException("Province not found: " + code));

    province.setName(req.name());
    return provinceRepo.save(province);
  }

  // DELETE
  @DeleteMapping("/{code}")
  public void delete(@PathVariable String code) {
    provinceRepo.deleteById(code);
  }
}
