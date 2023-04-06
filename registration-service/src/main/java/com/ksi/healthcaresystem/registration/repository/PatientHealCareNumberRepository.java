package com.ksi.healthcaresystem.registration.repository;

import com.ksi.healthcaresystem.registration.entity.PatientHealCareNumber;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientHealCareNumberRepository extends JpaRepository<PatientHealCareNumber, Long> {
  Optional<PatientHealCareNumber> findPatientHealCareNumberByHealthCareNumber(String healthCareReference);
}

