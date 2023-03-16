package com.ksi.healthcaresystem.registration.repository;

import com.ksi.healthcaresystem.registration.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}