package com.ksi.healthcaresystem.ward.repository;

import com.ksi.healthcaresystem.ward.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Long> {
}
