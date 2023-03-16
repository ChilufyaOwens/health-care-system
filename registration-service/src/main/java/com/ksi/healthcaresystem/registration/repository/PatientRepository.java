package com.ksi.healthcaresystem.registration.repository;

import com.ksi.healthcaresystem.registration.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
