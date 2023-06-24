package com.ksi.healthcaresystem.registration.repository;

import com.ksi.healthcaresystem.registration.entity.RegistrationMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationMessageRepository extends JpaRepository<RegistrationMessage, Long> {
}
