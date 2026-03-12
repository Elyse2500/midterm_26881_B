package com.attendance.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.entity.Province;

public interface ProvinceRepository extends JpaRepository<Province, String> {
}
