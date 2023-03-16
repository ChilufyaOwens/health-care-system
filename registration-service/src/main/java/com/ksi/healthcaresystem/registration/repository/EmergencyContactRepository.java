package com.ksi.healthcaresystem.registration.repository;

import com.ksi.healthcaresystem.registration.entity.EmergencyContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {

}