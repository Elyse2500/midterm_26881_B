package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
