package com.ksi.healthcaresystem.notificationservice.repository;

import com.ksi.healthcaresystem.notificationservice.entity.EmailDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailDetailsRepository extends JpaRepository<EmailDetails, Long> {
}
